package org.metatransapps.commons.cfg.publishedapp;


import org.metatransapps.commons.cfg.IConfigurationEntry_Obj;
import org.metatransapps.commons.cfg.appstore.IAppStore;


public interface IPublishedApplication extends IConfigurationEntry_Obj {
	
	//Google store IDs
	/*public static final int ID_CAFK_GOOGLE 		= 100;
	public static final int ID_MAFK_GOOGLE 		= 200;
	public static final int ID_EC_GOOGLE 		= 300;
	public static final int ID_EC_GPS_GOOGLE 	= 400;
	
	public static final int ID_CAFK_SAMSUNG		= 500;
	public static final int ID_MAFK_SAMSUNG 	= 600;
	public static final int ID_EC_SAMSUNG 		= 700;
	public static final int ID_EC_GPS_SAMSUNG 	= 800;
	
	public static final int ID_CAFK_AMAZON		= 900;
	public static final int ID_MAFK_AMAZON 		= 1000;
	public static final int ID_EC_AMAZON 		= 1100;
	public static final int ID_EC_GPS_AMAZON 	= 1200;
	
	public static final int ID_CAFK_YANDEX		= 1300;
	public static final int ID_MAFK_YANDEX 		= 1400;
	public static final int ID_EC_YANDEX 		= 1500;
	public static final int ID_EC_GPS_YANDEX 	= 1600;
	*/
	
	public String getPackage();
	
	public String getMarketURL();
	
	public IAppStore getAppStore();
	
	public boolean isSocial();
	
	public boolean isPaid();
	
	public IPublishedApplication getSocialVersion();
	
	public IPublishedApplication getPaidVersion();
	public void setPaidVersion(IPublishedApplication paidVersion);
	
}
