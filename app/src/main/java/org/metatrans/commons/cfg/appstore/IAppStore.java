package org.metatrans.commons.cfg.appstore;


public interface IAppStore {
	
	public static final int ID_DEFAULT			= 0;
	public static final int ID_GOOGLE			= 1;
	public static final int ID_AMAZON			= 2;
	public static final int ID_SAMSUNG			= 3;
	public static final int ID_YANDEX			= 4;
	public static final int ID_OPERA			= 5;
	public static final int ID_HUAWEI			= 6;
	public static final int ID_FDROID_OWN		= 7;
	public static final int ID_FDROID_OFFICIAL	= 8;
	
	public static final IAppStore OBJ_DEFAULT 			= new AppStore_Default();
	public static final IAppStore OBJ_GOOGLE 			= new AppStore_Google();
	public static final IAppStore OBJ_AMAZON 			= new AppStore_Amazon();
	public static final IAppStore OBJ_SAMSUNG 			= new AppStore_Samsung();
	public static final IAppStore OBJ_YANDEX 			= new AppStore_Yandex();
	public static final IAppStore OBJ_OPERA 			= new AppStore_Opera();
	public static final IAppStore OBJ_HUAWEI 			= new AppStore_Huawei();
	public static final IAppStore OBJ_FDROID_OWN 		= new AppStore_FDroid_own();
	public static final IAppStore OBJ_FDROID_OFFICIAL 	= new AppStore_FDroid_official();
	
	public int getID();
	public String getName();
}
