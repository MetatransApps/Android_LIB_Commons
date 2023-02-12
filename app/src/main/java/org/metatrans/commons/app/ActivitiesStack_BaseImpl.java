package org.metatrans.commons.app;


import android.app.Activity;

import java.util.ArrayList;
import java.util.List;


public class ActivitiesStack_BaseImpl implements IActivitiesStack {
	
	
	private List<Activity> stack;
	
	
	public ActivitiesStack_BaseImpl() {

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

		if (stack.size() == 0) {

			return null;
		}

		return stack.get(stack.size() - 1);
	}


	@Override
	public List<Activity> getActivitiesStack() {

		return stack;
	}
}
