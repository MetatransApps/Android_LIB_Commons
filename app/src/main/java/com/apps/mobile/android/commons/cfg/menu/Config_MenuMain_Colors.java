package com.apps.mobile.android.commons.cfg.menu;


import android.app.Activity;
import android.content.Intent;

import com.apps.mobile.android.commons.R;
import com.apps.mobile.android.commons.app.Application_Base;
import com.apps.mobile.android.commons.cfg.colours.ConfigurationUtils_Colours;
import com.apps.mobile.android.commons.menu.Activity_Menu_Colours_Base;


public class Config_MenuMain_Colors extends Config_MenuMain_Base {
	
	
	@Override
	public int getID() {
		return CFG_MENU_COLORS;
	}
	
	
	@Override
	public int getName() {
		return R.string.menu_colour_scheme;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_colours;
	}
	
	@Override
	public String getDescription_String() {
		return Application_Base.getInstance().getString(R.string.label_current) + ": " + Application_Base.getInstance().getString(ConfigurationUtils_Colours.getConfigByID(Application_Base.getInstance().getUserSettings().uiColoursID).getName());
	}
	
	@Override
	public Runnable getAction() {
		
		return new Runnable() {
			
			@Override
			public void run() {
				
				Activity currentActivity = Application_Base.getInstance().getCurrentActivity();
				if (currentActivity != null) {
					
					currentActivity.finish();
					
					Intent i = new Intent(currentActivity, Activity_Menu_Colours_Base.class);
					currentActivity.startActivity(i);
				}
			}
		};
	}
}
