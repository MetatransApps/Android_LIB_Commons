package com.apps.mobile.android.commons.cfg.menu;


import com.apps.mobile.android.commons.cfg.IConfigurationEntry;


public interface IConfigurationMenu_Main extends IConfigurationEntry {
	
	
	public static int CFG_MENU_COLORS 				= 1;
	public static int CFG_MENU_SCORES	 			= 2;
	public static int CFG_MENU_ACHIEVEMENTS			= 3;
	public static int CFG_MENU_MOREGAMES	 		= 4;
	public static int CFG_MENU_RATE_REVIEW_UPDATES 	= 5;
	public static int CFG_MENU_ABOUT		 		= 6;
	public static int CFG_MENU_HELP			 		= 7;
	public static int CFG_MENU_DESCRIPTION	 		= 8;
	public static int CFG_MENU_COMPANY_OFFLINE 		= 9;
	public static int CFG_MENU_COMPANY_ONLINE 		= 10;
	public static int CFG_MENU_PAID_VERSION	 		= 11;
	public static int CFG_MENU_INVITE_FRIENDS		= 12;
	
	public Runnable getAction();
}
