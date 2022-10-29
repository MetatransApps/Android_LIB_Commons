package org.metatrans.commons.cfg.menu;


import org.metatrans.commons.R;
import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.cfg.publishedapp.IPublishedApplication;
import org.metatrans.commons.cfg.publishedapp.MarketURLGen_OurWebsite;
import org.metatrans.commons.events.api.IEvent_Base;
import org.metatrans.commons.events.api.IEventsManager;
import org.metatrans.commons.web.WebUtils;

import android.app.Activity;


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
		//return R.drawable.ic_action_achievement_white;
		return R.drawable.ic_action_heart_white;
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

				IPublishedApplication app	 	= Application_Base.getInstance().getApp_Me();
				IPublishedApplication app_paid 	= app.getPaidVersion();

				Activity currentActivity = Application_Base.getInstance().getCurrentActivity();

				if (currentActivity != null) {

					if (app_paid == null) {

						WebUtils.openApplicationWebpage(currentActivity, app);

					} else {

						WebUtils.openApplicationStorePage(currentActivity, app_paid);
					}

					IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
					eventsManager.register(Application_Base.getInstance(), eventsManager.create(IEvent_Base.MENU_OPERATION, IEvent_Base.MENU_OPERATION_OPEN_PAID_VERSION,
							"MENU_OPERATION", "OPEN_PAID_VERSION"));
				}
			}
		};
	}
}
