package org.metatrans.commons.cfg.menu;


import android.app.Activity;
import android.content.Intent;

import org.metatrans.commons.R;
import org.metatrans.commons.app.Application_Base;
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
