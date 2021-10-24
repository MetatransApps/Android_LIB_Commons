package org.metatrans.commons.analytics;


import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.events.api.IEvent_Base;

import android.app.Activity;


public interface IAnalytics {
	
	public void init(Application_Base app_context);
	
	public void sendEvent(IEvent_Base event);
	
	public void onActivity_Create(Activity activity);

	public void onActivity_Destroy(Activity activity);
	
	public Activity getCurrentActivity();
}
