package org.metatrans.commons.app;


import android.app.Activity;

import java.util.List;


public interface IActivitiesStack {
	
	void onActivity_Create(Activity activity);

	void onActivity_Destroy(Activity activity);
	
	Activity getCurrentActivity();

	List<Activity> getActivitiesStack();
}
