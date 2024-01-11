package org.metatrans.commons.cfg.menu;


import android.app.Activity;
import android.content.Intent;

import org.metatrans.commons.R;
import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.cfg.melody.ConfigurationUtils_Melody;
import org.metatrans.commons.cfg.melody.IConfigurationMelody;
import org.metatrans.commons.cfg.sound.IConfigurationSound;
import org.metatrans.commons.melody.Activity_Menu_Melody;


public class Config_MenuMain_Melody extends Config_MenuMain_Base {
	
	
	@Override
	public int getID() {
		return CFG_MENU_MELODY;
	}
	
	
	@Override
	public int getName() {
		return R.string.melody_title;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_action_music_3;
	}
	
	
	@Override
	public int getDescription() {
		return 0;
	}


	@Override
	public String getDescription_String() {

		int melody_cfg_id = Application_Base.getInstance().getUserSettings().melody_cfg_id;

		IConfigurationMelody melody_cfg = ConfigurationUtils_Melody.getConfigByID(melody_cfg_id);

		return Application_Base.getInstance().getString(melody_cfg.getName());
	}


	@Override
	public Runnable getAction() {
		
		return new Runnable() {
			
			@Override
			public void run() {

				Activity currentActivity = Application_Base.getInstance().getCurrentActivity();
				if (currentActivity != null) {

					currentActivity.finish();

					Intent i = new Intent(currentActivity, Activity_Menu_Melody.class);
					currentActivity.startActivity(i);
				}
			}
		};
	}
}
