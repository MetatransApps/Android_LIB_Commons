package org.metatrans.commons.cfg.menu;


import org.metatrans.commons.R;
import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.events.api.IEvent_Base;
import org.metatrans.commons.events.api.IEventsManager;
import org.metatrans.commons.web.Activity_WebView_StatePreservingImpl_With_VideoPlayer;

import android.app.Activity;
import android.content.Intent;


public class Config_MenuMain_Company_Online extends Config_MenuMain_Base {
	
	
	@Override
	public int getID() {
		return CFG_MENU_COMPANY_ONLINE;
	}
	
	
	@Override
	public int getName() {
		return R.string.company_home_online;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_metatrans_online;
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
		           	Intent intent = new Intent(currentActivity, Activity_WebView_StatePreservingImpl_With_VideoPlayer.class);
	            	intent.putExtra("URL", Application_Base.getInstance().getAppConfig().getCompanyWebSiteURL());
	            	intent.putExtra("titleID", getName());
	            	currentActivity.startActivity(intent);
	            	
	            	IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
	            	eventsManager.register(Application_Base.getInstance(), eventsManager.create(IEvent_Base.MENU_OPERATION, IEvent_Base.MENU_OPERATION_OPEN_COMPANY_ONLINE,
							"MENU_OPERATION", "OPEN_COMPANY_ONLINE"));
				}
			}
		};
	}
}
