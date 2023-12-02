package org.metatrans.commons.app;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.metatrans.commons.Alerts_Base;
import org.metatrans.commons.achievements.IAchievementsManager;
import org.metatrans.commons.cfg.appstore.IAppStore;
import org.metatrans.commons.cfg.colours.ConfigurationUtils_Colours;
import org.metatrans.commons.cfg.colours.IConfigurationColours;
import org.metatrans.commons.cfg.publishedapp.IPublishedApplication;
import org.metatrans.commons.cfg.publishedapp.PublishedApplication_Utils;
import org.metatrans.commons.engagement.EngagementProvider_Base;
import org.metatrans.commons.engagement.IAchievementsProvider;
import org.metatrans.commons.engagement.IEngagementProvider;
import org.metatrans.commons.engagement.ILeaderboardsProvider;
import org.metatrans.commons.engagement.ISocialProvider;
import org.metatrans.commons.engagement.achievements.AchievementsProvider_Base;
import org.metatrans.commons.engagement.social.SocialProvider_Dummy;
import org.metatrans.commons.events.EventSender_BaseImpl;
import org.metatrans.commons.events.EventsManager_Base;
import org.metatrans.commons.events.IEventSender;
import org.metatrans.commons.events.api.IEventsManager;
import org.metatrans.commons.melody.IMelodiesManager;
import org.metatrans.commons.model.GameData_Base;
import org.metatrans.commons.model.UserData_Base;
import org.metatrans.commons.model.UserSettings_Base;
import org.metatrans.commons.sfx.ISFXManager;
import org.metatrans.commons.sfx.SFXManager_SoundPoolImpl;
import org.metatrans.commons.storage.StorageUtils;
import org.metatrans.commons.web.WebUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.widget.CompoundButton;


public abstract class Application_Base extends Application {
	
	
	private static final String[] KEYWORDS = new String[] {"games"};

	private static Application_Base singleton;


	private ExecutorService executor;


	private IEngagementProvider engagementProvider;

	private IAchievementsManager achievementsManager;

	private IActivitiesStack acitvities_stack = new ActivitiesStack_BaseImpl();

	private IEventsManager eventsManager;

	private IEventSender eventSender = new EventSender_BaseImpl();

	private IMelodiesManager melodiesManager;


	private Class <? extends UserSettings_Base> settings_latest_model_class;

	private Class <? extends UserData_Base> userdata_latest_model_class;

	private Class <? extends GameData_Base>  gamedata_latest_model_class;


	@Override
	public void onCreate() {
		
		super.onCreate();
		//Called when the application is starting, before any other application objects have been created.
		
		System.out.println("Application_Base: onCreate called " + System.currentTimeMillis());
		
		
		singleton = this;
		
		
		executor = Executors.newCachedThreadPool();

		engagementProvider = new EngagementProvider_Base();
		
		
		//Create managers
		achievementsManager = createAchievementsManager();
		
		eventsManager = createEventsManager();

		eventsManager.init(this);
		
		
		//Create providers
		getEngagementProvider().setSocialProvider(createSocialProvider());
		getEngagementProvider().setLeaderboardsProvider(createLeaderboardsProvider());
		getEngagementProvider().setAchievementsProvider(createAchievementsProvider());
		
		
		ConfigurationUtils_Colours.class.getName();

		setMelodiesManager(new IMelodiesManager() {
			@Override
			public void setMelody(int melody_cfg_id) {

			}

			@Override
			public void stop() {

			}
		});

		UserSettings_Base settings_test = createUserSettingsObject();
		settings_latest_model_class = settings_test.getClass();

		UserData_Base userdata_test = createUserDataObject();
		userdata_latest_model_class = userdata_test.getClass();

		GameData_Base gameData_test = createGameDataObject();
		gamedata_latest_model_class = gameData_test.getClass();
	}


	protected void setMelodiesManager(IMelodiesManager _melodiesManager) {

		melodiesManager = _melodiesManager;
	}


	public IMelodiesManager getMelodiesManager() {

		return melodiesManager;
	}


	public boolean supportMelodies() {

		return false;
	}


	public ISFXManager getSFXManager() {

		//return SFXManager_BaseImpl.getSingleton();
		return SFXManager_SoundPoolImpl.getSingleton();
	}


	/**
	 * It is used for showing appropriate ads where necessary
	 */
	public boolean isChildDirected() {

		return false;
	}


	public String[] getKeywords() {
		return KEYWORDS;
	}
	
	
	protected ISocialProvider createSocialProvider() {
		return new SocialProvider_Dummy(this);
	}
	
	
	protected ILeaderboardsProvider createLeaderboardsProvider() {
		return null;
	}
	
	
	protected IAchievementsProvider createAchievementsProvider() {
		return new AchievementsProvider_Base(this);
	}
	
	
	protected IAchievementsManager createAchievementsManager() {

		return null;
	}
	
	
	protected IEventsManager createEventsManager() {

		return new EventsManager_Base(executor);
	}
	
	
	public IEngagementProvider getEngagementProvider() {
		return engagementProvider;
	}
	
	
	public IEventsManager getEventsManager() {
		return eventsManager;
	}


	public IEventSender getEventSender() {

		return eventSender;
	}


	public IAchievementsManager getAchievementsManager() {
		return achievementsManager;
	}


	public IActivitiesStack getActivitiesStack() {

		return acitvities_stack;
	}


	public abstract boolean isTestMode();


	protected abstract UserSettings_Base createUserSettingsObject();

	
	public UserSettings_Base getUserSettings() {

		//System.out.println("Application_Base.getUserSettings: called");

		UserSettings_Base settings;

		try {

			settings = (UserSettings_Base) StorageUtils.readStorage(this, UserSettings_Base.FILE_NAME_USER_SETTINGS);

			if (settings == null) {

				System.out.println("Application_Base.getUserSettings: settings == null ... recreateUserSettings");

				recreateUserSettings();

				settings = (UserSettings_Base) StorageUtils.readStorage(this, UserSettings_Base.FILE_NAME_USER_SETTINGS);

			} else if (settings.model_version != UserSettings_Base.MODEL_VERSION_LATEST) {

				//We have new model version and must re-create the object.
				System.out.println("Application_Base.getUserSettings: settings.model_version != UserSettings_Base.MODEL_VERSION_LATEST ... recreateGameDataObject");

				recreateUserSettings();

				settings = (UserSettings_Base) StorageUtils.readStorage(this, UserSettings_Base.FILE_NAME_USER_SETTINGS);

			} else {

				//Check if model class has changed from prev version
				if (!settings_latest_model_class.equals(settings.getClass())) {

					System.out.println("Application_Base.getUserSettings: !settings_latest_model_class.equals(settings.getClass())");

					recreateUserSettings();
					settings = (UserSettings_Base) StorageUtils.readStorage(this, UserSettings_Base.FILE_NAME_USER_SETTINGS);
				}
			}

		} catch (Exception e) {

			System.out.println("Application_Base.getUserSettings: Exception");

			if (isTestMode()) {

				throw e;
			}

			e.printStackTrace();

			//In case of incompatible change of UserSettings class and failed deserialization, we lose the current settings and create new one
			recreateUserSettings();

			settings = (UserSettings_Base) StorageUtils.readStorage(this, UserSettings_Base.FILE_NAME_USER_SETTINGS);
		}

		return settings;
	}


	public void recreateUserSettings() {

		System.out.println("Application_Base.recreateUserSettings");

		UserSettings_Base settings = createUserSettingsObject();
		StorageUtils.writeStore(this, UserSettings_Base.FILE_NAME_USER_SETTINGS, settings);

		settings = (UserSettings_Base) StorageUtils.readStorage(this, UserSettings_Base.FILE_NAME_USER_SETTINGS);
	}


	public void storeUserSettings() {
		storeUserSettings(getUserSettings());
	}


	public void storeUserSettings(UserSettings_Base settings) {

		System.out.println("Application_Base.storeUserSettings");

		StorageUtils.writeStore(this, UserSettings_Base.FILE_NAME_USER_SETTINGS, settings);
	}


	protected UserData_Base createUserDataObject() {

		return new UserData_Base();
	}


	public UserData_Base getUserData() {

		//System.out.println("Application_Base.getUserData: called");

		UserData_Base user_data;

		try {

			user_data = (UserData_Base) StorageUtils.readStorage(this, UserData_Base.FILE_NAME_USER_DATA);

			if (user_data == null) {

				System.out.println("Application_Base.getUserData: user_data == null ... recreateUserData");

				recreateUserData();

				user_data = (UserData_Base) StorageUtils.readStorage(this, UserData_Base.FILE_NAME_USER_DATA);

			} else if (user_data.getModelVersion() != UserData_Base.MODEL_VERSION_LATEST) {

				//We have new model version and must re-create the object.
				System.out.println("Application_Base.getUserData: user_data.model_version != UserData_Base.MODEL_VERSION_LATEST ... recreating object");

				recreateUserData();

				user_data = (UserData_Base) StorageUtils.readStorage(this, UserData_Base.FILE_NAME_USER_DATA);

			} else {

				//Check if model class has changed from prev version
				if (!userdata_latest_model_class.equals(user_data.getClass())) {

					System.out.println("Application_Base.getUserData: !userdata_latest_model_class.equals(user_data.getClass())");

					recreateUserData();

					user_data = (UserData_Base) StorageUtils.readStorage(this, UserData_Base.FILE_NAME_USER_DATA);
				}
			}

		} catch (Exception e) {

			System.out.println("Application_Base.getUserData: Exception");

			if (isTestMode()) {

				throw e;
			}

			e.printStackTrace();

			//In case of incompatible change of UserData class and failed deserialization, we lose the current settings and create new one
			recreateUserData();

			user_data = (UserData_Base) StorageUtils.readStorage(this, UserData_Base.FILE_NAME_USER_DATA);
		}

		return user_data;
	}


	public void recreateUserData() {

		System.out.println("Application_Base.recreateUserData");

		UserData_Base user_data = createUserDataObject();

		StorageUtils.writeStore(this, UserData_Base.FILE_NAME_USER_DATA, user_data);

		user_data = (UserData_Base) StorageUtils.readStorage(this, UserData_Base.FILE_NAME_USER_DATA);
	}


	public void storeUserData() {
		storeUserData(getUserData());
	}


	public void storeUserData(UserData_Base user_data) {

		System.out.println("Application_Base.storeUserData");

		StorageUtils.writeStore(this, UserData_Base.FILE_NAME_USER_DATA, user_data);
	}


	protected abstract GameData_Base createGameDataObject();


	public GameData_Base getGameData() {

		GameData_Base gameData;

		try {

			gameData = (GameData_Base) StorageUtils.readStorage(this, GameData_Base.FILE_NAME_GAME_DATA);

			if (gameData == null) {

				System.out.println("Application_Base.getGameData: gameData == null ... recreateGameDataObject");

				recreateGameDataObject();

				gameData = (GameData_Base) StorageUtils.readStorage(this, GameData_Base.FILE_NAME_GAME_DATA);

			} else if (gameData.model_version != GameData_Base.MODEL_VERSION_LATEST) {

				//We have new model version and must re-create the object.
				System.out.println("Application_Base.getGameData: gameData.model_version != GameData_Base.MODEL_VERSION_LATEST ... recreateGameDataObject");

				recreateGameDataObject();

				gameData = (GameData_Base) StorageUtils.readStorage(this, GameData_Base.FILE_NAME_GAME_DATA);

			} else if (!gamedata_latest_model_class.equals(gameData.getClass())) {

				//Check if model class has changed from prev version
				System.out.println("Application_Base.getGameData: !gamedata_latest_model_class.equals(gameData.getClass()) ... recreateGameDataObject");

				recreateGameDataObject();

				gameData = (GameData_Base) StorageUtils.readStorage(this, GameData_Base.FILE_NAME_GAME_DATA);
			}

		} catch (Exception e) {

			System.out.println("Application_Base.getGameData: Exception.");

			if (isTestMode()) {

				System.out.println("Application_Base.getGameData: throw the Exception in the current mode - TestMode.");

				throw e;
			}

			e.printStackTrace();

			System.out.println("Application_Base.getGameData: Exception covered ... recreateGameDataObject");

			//In case of incompatible change of GameData class and failed deserialization or BoardManager creation, we lose the current game and create new one
			recreateGameDataObject();

			gameData = (GameData_Base) StorageUtils.readStorage(this, GameData_Base.FILE_NAME_GAME_DATA);
		}

		/*IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
		if (getInstance().getCurrentActivity() != null) {
			eventsManager.handleGameEvents_OnStart(getInstance().getCurrentActivity(), data);
		}*/

		return gameData;
	}


	public void storeGameData() {
		storeGameData(getGameData());
	}


	public void storeGameData(GameData_Base gameData) {

		System.out.println("Application_Base.storeGameData");

		StorageUtils.writeStore(this, GameData_Base.FILE_NAME_GAME_DATA, gameData);
	}


	public void recreateGameDataObject() {

		System.out.println("Application_Base.recreateGameDataObject");

		GameData_Base data = createGameDataObject();
		StorageUtils.writeStore(this, GameData_Base.FILE_NAME_GAME_DATA, data);

		data = (GameData_Base) StorageUtils.readStorage(this, GameData_Base.FILE_NAME_GAME_DATA);

		//IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
		//eventsManager.handleGameEvents_OnStart(Application_Base.getInstance().getCurrentActivity(), data);
	}
	
	
	public IConfigurationColours getColoursCfg() {
		return ConfigurationUtils_Colours.getConfigByID(getUserSettings().uiColoursID);
	}
	
	
	public Activity getCurrentActivity() {

		return getActivitiesStack().getCurrentActivity();
	}
	
	
	public static Application_Base getInstance() {
		if (singleton == null) {
			throw new IllegalStateException("Application singleton is null");
		}
		return singleton;
	}
	
	
	public ExecutorService getExecutor() {
		return executor;
	}
	
	
	public IAppStore getAppStore() {
		return IAppStore.OBJ_GOOGLE;
	}
	
	
	public IPublishedApplication getApp_Me() {
		
		IPublishedApplication app = null;
		
		List<IPublishedApplication> apps = PublishedApplication_Utils.getStoreApps(getAppStore());
		
		for (IPublishedApplication cur: apps) {
			
			if (cur.getPackage().equals(getPackageName())) {
				app = cur;
				break;
			}
		}
		
		if (app == null) {

			//throw new IllegalStateException("IPublishedApplication with package " + getPackageName() + " not found.");
		}
		
		return app;
	}


	public void showRateAppDialog(Activity activity) {

		//No way to give ratings on F-Droid stores
		if (getAppStore().equals(IAppStore.OBJ_FDROID_OFFICIAL)
			|| getAppStore().equals(IAppStore.OBJ_FDROID_OWN)
			) {

			return;
		}

		if (!Application_Base.getInstance().getUserSettings().dont_show_alert_rate_app) {

			if (Application_Base.getInstance().getUserData().showAppRatingDialog()) {


				DialogInterface.OnClickListener positive = 	new DialogInterface.OnClickListener() {

					//User accepted to give feedback
					@Override
					public void onClick(DialogInterface dialog, int which) {

						try {

							WebUtils.openApplicationStorePage(Application_Base.this, Application_Base.getInstance().getApp_Me());

						} catch (Exception e) {

							e.printStackTrace();
						}
					}
				};

				DialogInterface.OnClickListener negative = 	new DialogInterface.OnClickListener() {

					//User rejected to give feedback
					@Override
					public void onClick(DialogInterface dialog, int which) {

						//Do nothing
					}
				};

				DialogInterface.OnCancelListener cancel = new DialogInterface.OnCancelListener() {

					//User canceled the dialog
					@Override
					public void onCancel(DialogInterface dialog) {

						//Do nothing
					}
				};

				CompoundButton.OnCheckedChangeListener disable = new CompoundButton.OnCheckedChangeListener() {

					//User don't want to see again this dialog
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

						try {

							Application_Base.getInstance().getUserSettings().dont_show_alert_rate_app = isChecked;

							Application_Base.getInstance().storeUserSettings();

						} catch (Exception e) {

							e.printStackTrace();
						}
					}
				};


				//ALERT FOR RATE APP
				int count_shown = Application_Base.getInstance().getUserData().getCountAppRatingDialogShown();

				AlertDialog.Builder adb;

				if (count_shown <= 2) {

					adb = Alerts_Base.createAlertDialog_RateApp(
							activity, positive, negative, cancel
					);

				} else {

					adb = Alerts_Base.createAlertDialog_RateApp(
							activity, positive, negative, cancel, disable
					);
				}

				adb.show();

				Application_Base.getInstance().getUserData().appRatingDialogShown();
			}
		}
	}
}
