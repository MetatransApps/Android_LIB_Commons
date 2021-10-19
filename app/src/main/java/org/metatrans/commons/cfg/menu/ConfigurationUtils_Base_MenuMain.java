package org.metatrans.commons.cfg.menu;


import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.cfg.ConfigurationUtils_Base;
import org.metatrans.commons.cfg.IConfigurationEntry;


public class ConfigurationUtils_Base_MenuMain extends ConfigurationUtils_Base {
	
	
	private static final String TAG_NAME = ConfigurationUtils_Base_MenuMain.class.getName();
	
	
	public static ConfigurationUtils_Base_MenuMain getInstance() {
		return (ConfigurationUtils_Base_MenuMain) getInstance(TAG_NAME);
	}
	
	
	public static void createInstance() {
		
		IConfigurationEntry[] cfgs_difficulties = null;
		
		//SHIT: FIX this if-else in one block
		if (Application_Base.getInstance().getApp_Me().getPaidVersion() != null) {
			
			cfgs_difficulties = new IConfigurationEntry[] { 

					new Config_MenuMain_Colors(),
					new Config_MenuMain_InviteFriends(),
					new Config_MenuMain_RateReviewUpgrades(),
					new Config_MenuMain_Exit(),
					new Config_MenuMain_PaidVersion(),
					new Config_MenuMain_MoreGames(),
					//new Config_MenuMain_Help(),
					//new Config_MenuMain_Description(),
					//new Config_MenuMain_About(),
					//new Config_MenuMain_Company_Offline(),
					//new Config_MenuMain_Company_Online(),
			};
		} else {
			
			cfgs_difficulties = new IConfigurationEntry[] {

					new Config_MenuMain_Colors(),
					new Config_MenuMain_InviteFriends(),
					new Config_MenuMain_RateReviewUpgrades(),
					new Config_MenuMain_Exit(),
					new Config_MenuMain_MoreGames(),
					//new Config_MenuMain_Help(),
					//new Config_MenuMain_Description(),
					//new Config_MenuMain_About(),
					//new Config_MenuMain_Company_Offline(),
					//new Config_MenuMain_Company_Online(),
			};
		}
		
		createInstance(TAG_NAME, new ConfigurationUtils_Base_MenuMain(), cfgs_difficulties);
	}
}
