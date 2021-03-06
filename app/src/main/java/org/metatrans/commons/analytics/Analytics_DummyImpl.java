package org.metatrans.commons.analytics;


import java.util.ArrayList;
import java.util.List;

import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.events.api.IEvent_Base;

import android.app.Activity;


public class Analytics_DummyImpl implements IAnalytics {
	
	
	private List<Activity> stack;
	
	
	public Analytics_DummyImpl() { 
		stack = new ArrayList<Activity>();
	}


	@Override
	public void onActivity_Create(Activity activity) {

		System.out.println("Analytics_ActivitiesStack: onActivity_Create: " + activity);
		stack.add(activity);
	}


	@Override
	public void onActivity_Destroy(Activity activity) {

		boolean last_found = stack.remove(activity);

		if (!last_found) {

			throw new IllegalStateException();
		}

		System.out.println("Analytics_ActivitiesStack: onActivity_Destroy: " + activity);
	}


	@Override
	public Activity getCurrentActivity() {
		//System.out.println("Current activity: " + current);
		if (stack.size() == 0) {
			return null;
		}

		return stack.get(stack.size() - 1);
	}


	@Override
	public void init(Application_Base app_context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendEvent(IEvent_Base event) {
		// TODO Auto-generated method stub

	}
}
