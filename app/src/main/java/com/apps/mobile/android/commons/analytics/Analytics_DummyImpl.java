package com.apps.mobile.android.commons.analytics;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.apps.mobile.android.commons.app.Application_Base;
import com.apps.mobile.android.commons.events.api.IEvent_Base;


public class Analytics_DummyImpl implements IAnalytics {
	
	
	private List<Activity> stack;
	
	
	public Analytics_DummyImpl() { 
		stack = new ArrayList<Activity>();
	}
	
	
	@Override
	public void init(Application_Base app_context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendEvent(IEvent_Base event) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onActivity_Create(Activity activity) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onActivity_Start(Activity activity) {
		//System.out.println("Start activity: " + activity);
		stack.add(activity);
	}

	@Override
	public void onActivity_Stop(Activity activity) {
		boolean last_found = stack.remove(activity);
		if (!last_found) {
			throw new IllegalStateException();
		}
	}

	@Override
	public Activity getCurrentActivity() {
		//System.out.println("Current activity: " + current);
		if (stack.size() == 0) {
			return null;
		}
		
		return stack.get(stack.size() - 1);
	}
}
