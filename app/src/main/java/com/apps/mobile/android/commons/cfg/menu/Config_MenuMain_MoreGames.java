package com.apps.mobile.android.commons.cfg.menu;


import android.app.Activity;
import android.content.Intent;

import com.apps.mobile.android.commons.R;
import com.apps.mobile.android.commons.app.Application_Base;
import com.apps.mobile.android.commons.marketing.Activity_Marketing_AppList;


public class Config_MenuMain_MoreGames extends Config_MenuMain_Base {
	
	
	@Override
	public int getID() {
		return CFG_MENU_MOREGAMES;
	}
	
	
	@Override
	public int getName() {
		return R.string.label_moregames;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_colours_tube;
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
					Intent i = new Intent(currentActivity, Activity_Marketing_AppList.class);
					currentActivity.startActivity(i);
				}
			}
		};
	}
}
