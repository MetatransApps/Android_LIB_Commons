package org.metatrans.commons.app;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.metatrans.commons.achievements.AchievementsManager_Base;
import org.metatrans.commons.achievements.IAchievementsManager;
import org.metatrans.commons.analytics.Analytics_DummyImpl;
import org.metatrans.commons.analytics.IAnalytics;
import org.metatrans.commons.cfg.app.IAppConfig;
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
import org.metatrans.commons.events.EventsManager_Base;
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
	
	private IEventsManager eventsManager;
	
	private IAnalytics analytics_dummy = new Analytics_DummyImpl();
	
	
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
	}
	
	
	public IAppConfig getAppConfig() {
		throw new UnsupportedOperationException();
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
		//return new AchievementsManager_Base(this);
	}
	
	
	protected IEventsManager createEventsManager() {
		return new EventsManager_Base(executor, getAnalytics());
	}
	
	
	
	public IEngagementProvider getEngagementProvider() {
		return engagementProvider;
	}
	
	
	public IEventsManager getEventsManager() {
		return eventsManager;
	}
	
	
	public IAchievementsManager getAchievementsManager() {
		return achievementsManager;
	}
	
	
	public IAnalytics getAnalytics() {
		return analytics_dummy;
	}


	public abstract boolean isTestMode();


	protected abstract UserSettings_Base createUserSettingsObject();

	
	public UserSettings_Base getUserSettings() {
		UserSettings_Base settings = (UserSettings_Base) StorageUtils.readStorage(this, UserSettings_Base.FILE_NAME_USER_SETTINGS);
		if (settings == null) {
			settings = createUserSettingsObject();
			StorageUtils.writeStore(this, UserSettings_Base.FILE_NAME_USER_SETTINGS, settings);
			settings = (UserSettings_Base) StorageUtils.readStorage(this, UserSettings_Base.FILE_NAME_USER_SETTINGS);
		}
		return settings;
	}
	
	
	protected abstract GameData_Base createGameDataObject();
	
	
	public GameData_Base getGameData() {
		GameData_Base data = (GameData_Base) StorageUtils.readStorage(this, GameData_Base.FILE_NAME_GAME_DATA);
		if (data == null) {
			data = createGameDataObject();
			StorageUtils.writeStore(this, GameData_Base.FILE_NAME_GAME_DATA, data);
			data = (GameData_Base) StorageUtils.readStorage(this, GameData_Base.FILE_NAME_GAME_DATA);
			
			/*IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
			if (getInstance().getCurrentActivity() != null) {
				eventsManager.handleGameEvents_OnStart(getInstance().getCurrentActivity(), data);
			}*/
		}
		return data;
	}
	
	
	public void storeGameData() {
		storeGameData(getGameData());
	}
	
	
	public void storeGameData(GameData_Base gameData) {
		
		System.out.println("GAMEDATA Store");
		
		StorageUtils.writeStore(this, GameData_Base.FILE_NAME_GAME_DATA, gameData);
	}
	
	
	public void recreateGameDataObject() {
		
		System.out.println("GAMEDATA recreateGameDataObject");
		
		GameData_Base data = createGameDataObject();
		StorageUtils.writeStore(this, GameData_Base.FILE_NAME_GAME_DATA, data);
		StorageUtils.readStorage(this, GameData_Base.FILE_NAME_GAME_DATA);
		
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
		StorageUtils.writeStore(this, UserSettings_Base.FILE_NAME_USER_SETTINGS, settings);
	}
	
	
	public Activity getCurrentActivity() {
		return getAnalytics().getCurrentActivity();
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
			throw new IllegalStateException("IPublishedApplication with package " + getPackageName() + " not found.");
		}
		
		return app;
	}
}
