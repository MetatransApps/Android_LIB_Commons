package org.metatrans.commons.cfg.menu;


import org.metatrans.commons.R;
import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.events.api.IEvent_Base;
import org.metatrans.commons.events.api.IEventsManager;
import org.metatrans.commons.web.WebUtils;

import android.app.Activity;


public class Config_MenuMain_RateReviewUpgrades extends Config_MenuMain_Base {
	
	
	@Override
	public int getID() {
		return CFG_MENU_RATE_REVIEW_UPDATES;
	}
	
	
	@Override
	public int getName() {
		return R.string.menu_rate_review_upgrades_title;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_hearts;
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
					WebUtils.openApplicationStorePage(currentActivity, Application_Base.getInstance().getApp_Me());
					
					try {
				    	IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
				    	eventsManager.register(Application_Base.getInstance(), eventsManager.create(IEvent_Base.MENU_OPERATION, IEvent_Base.MENU_OPERATION_CHECKFORUPDATES,
								"MENU_OPERATION", "CHECKFORUPDATES"));
			    	} catch(Exception e) {
			    		e.printStackTrace();
			    	}
				}
			}
		};
	}
}
