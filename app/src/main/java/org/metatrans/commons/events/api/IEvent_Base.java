package org.metatrans.commons.events.api;


import java.io.Serializable;


public interface IEvent_Base extends Serializable {
	
	
	/*
	 * Methods
	 */
	public int getID();
	public int getSubID();
	public int getSubSubID();
	
	public String getName();
	public String getSubName();
	public String getSubSubName();
	
	public long getValue();
	
	
	/*
	 * Categories
	 */
	public static final int LOADING				 					= 1;
	
	public static final int WIN_GAME	 							= 2;	
	public static final int START_GAME		 						= 3;
	public static final int EXIT_GAME		 						= 4;
	public static final int MENU_OPERATION 							= 5;
	
	public static final int NOTIFICATIONS							= 7;
	public static final int CONNECTIVENESS							= 8;
	public static final int MARKETING	 							= 9;
	
	
	/*
	 * Sub categories
	 */
	public static final int LOADING_STOPPED_PIECE 					= 1;
	public static final int LOADING_STOPPED_PIECES 					= 2;
	
	public static final int EXIT_GAME_TOTAL 						= 1;
	public static final int EXIT_GAME_MODE							= 3;
	public static final int EXIT_GAME_COLOURS						= 4;
	public static final int EXIT_GAME_SCORES						= 7;
	public static final int EXIT_GAME_ONLINE						= 8;
	public static final int EXIT_GAME_STORE							= 9;
	
	public static final int MENU_OPERATION_OPEN_HELP				= 1;
	public static final int MENU_OPERATION_OPEN_ABOUT				= 2;
	public static final int MENU_OPERATION_CHANGE_COLOURS			= 3;
	public static final int MENU_OPERATION_CHANGE_MODE				= 4;
	public static final int MENU_OPERATION_RATEIT					= 7;
	public static final int MENU_OPERATION_ACHIEVEMENTS				= 9;
	public static final int MENU_OPERATION_SCORES					= 10;
	public static final int MENU_OPERATION_OPEN_HELP_SCORES			= 11;
	public static final int MENU_OPERATION_NEW_GAME					= 14;
	public static final int MENU_OPERATION_SHARE					= 15;
	public static final int MENU_OPERATION_CHECKFORUPDATES			= 16;
	public static final int MENU_OPERATION_ENG_PROV_ACHIEVEMENTS	= 20;
	public static final int MENU_OPERATION_ENG_PROV_LEADERBOARDS	= 21;
	public static final int MENU_OPERATION_OPEN_DESCRIPTION			= 22;
	public static final int MENU_OPERATION_OPEN_COMPANY_ONLINE		= 23;
	public static final int MENU_OPERATION_OPEN_COMPANY_OFFLINE		= 24;
	public static final int MENU_OPERATION_OPEN_PAID_VERSION		= 25;
	public static final int MENU_OPERATION_CHANGE_SOUND				= 26;

	public static final int NOTIFICATIONS_TIMEOFF_REMINDER_FIRED	= 1;
	public static final int NOTIFICATIONS_TIMEOFF_REMINDER_MISSED 	= 2;
	public static final int NOTIFICATIONS_TIMEOFF_REMINDER_CLICKED 	= 3;
	
	public static final int CONNECTIVENESS_BOOTS			 		= 1;
	public static final int CONNECTIVENESS_DAILY_PING		 		= 2;
	
	public static final int MARKETING_APPLIST_ITEM_CLICKED			= 1;
	public static final int MARKETING_HOME_AD_INITIAL_CLICKED		= 2;
	public static final int MARKETING_INVITE_FRIENDS_CLICKED		= 3;
	public static final int MARKETING_HOME_AD_PROVIDER_CLICKED		= 4;
	public static final int MARKETING_HOME_AD_INTERSTITIAL_OPENED	= 5;
	
}
