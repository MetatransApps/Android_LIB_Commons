package com.apps.mobile.android.commons.cfg.menu;


import android.app.Activity;

import com.apps.mobile.android.commons.R;
import com.apps.mobile.android.commons.app.Application_Base;
import com.apps.mobile.android.commons.events.api.IEvent_Base;
import com.apps.mobile.android.commons.events.api.IEventsManager;
import com.apps.mobile.android.commons.web.WebUtils;


public class Config_MenuMain_PaidVersion extends Config_MenuMain_Base {
	
	
	@Override
	public int getID() {
		return CFG_MENU_PAID_VERSION;
	}
	
	
	@Override
	public int getName() {
		return R.string.buy_title;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_paid_version;
	}
	
	
	@Override
	public int getDescription() {
		return R.string.buy_desc;
	}
	
	
	@Override
	public Runnable getAction() {
		
		return new Runnable() {
			
			@Override
			public void run() {
				
				Activity currentActivity = Application_Base.getInstance().getCurrentActivity();
				if (currentActivity != null) {
	            	WebUtils.openApplicationStorePage(currentActivity,
	            			Application_Base.getInstance().getApp_Me().getPaidVersion());
	            	
	            	IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
	            	eventsManager.register(Application_Base.getInstance(), eventsManager.create(IEvent_Base.MENU_OPERATION, IEvent_Base.MENU_OPERATION_OPEN_PAID_VERSION,
							"MENU_OPERATION", "OPEN_PAID_VERSION"));
				}
			}
		};
	}
}
