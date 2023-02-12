package org.metatrans.commons.app;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
import org.metatrans.commons.model.GameData_Base;
import org.metatrans.commons.model.UserSettings_Base;
import org.metatrans.commons.storage.StorageUtils;

import android.app.Activity;
import android.app.Application;


public abstract class Application_Base extends Application {
	
	
	private static final String[] KEYWORDS = new String[] {"games"};

	private static Application_Base singleton;


	private ExecutorService executor;


	private IEngagementProvider engagementProvider;

	private IAchievementsManager achievementsManager;

	private IActivitiesStack acitvities_stack = new ActivitiesStack_BaseImpl();

	private IEventsManager eventsManager;

	private IEventSender eventSender = new EventSender_BaseImpl();


	private Class <? extends UserSettings_Base> settings_latest_model_class;

	private Class <? extends GameData_Base>  gamedata_latest_model_class;

	protected UserSettings_Base settings_test;


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


		settings_test = createUserSettingsObject();
		settings_latest_model_class = settings_test.getClass();

		GameData_Base gameData_test = createGameDataObject();
		gamedata_latest_model_class = gameData_test.getClass();

		settings_test = null;

		//StorageUtils.clearStore(this, UserSettings_Base.FILE_NAME_USER_SETTINGS);
		//StorageUtils.clearStore(this, GameData_Base.FILE_NAME_GAME_DATA);
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

				//We have new model version and must re-create the GameData.
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

				//We have new model version and must re-create the GameData.
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


	public void storeUserSettings() {
		storeUserSettings(getUserSettings());
	}


	public void storeUserSettings(UserSettings_Base settings) {

		System.out.println("Application_Base.storeUserSettings");

		StorageUtils.writeStore(this, UserSettings_Base.FILE_NAME_USER_SETTINGS, settings);
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
}
