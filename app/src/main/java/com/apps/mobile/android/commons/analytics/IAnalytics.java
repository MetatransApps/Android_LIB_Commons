package com.apps.mobile.android.commons.analytics;


import android.app.Activity;

import com.apps.mobile.android.commons.app.Application_Base;
import com.apps.mobile.android.commons.events.api.IEvent_Base;


public interface IAnalytics {
	
	public void init(Application_Base app_context);
	
	public void sendEvent(IEvent_Base event);
	
	public void onActivity_Create(Activity activity);
	
	public void onActivity_Start(Activity activity);
	
	public void onActivity_Stop(Activity activity);
	
	public Activity getCurrentActivity();
}
