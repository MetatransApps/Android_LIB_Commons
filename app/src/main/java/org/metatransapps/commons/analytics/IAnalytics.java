package org.metatransapps.commons.analytics;


import org.metatransapps.commons.app.Application_Base;
import org.metatransapps.commons.events.api.IEvent_Base;

import android.app.Activity;


public interface IAnalytics {
	
	public void init(Application_Base app_context);
	
	public void sendEvent(IEvent_Base event);
	
	public void onActivity_Create(Activity activity);
	
	public void onActivity_Start(Activity activity);
	
	public void onActivity_Stop(Activity activity);
	
	public Activity getCurrentActivity();
}
