package org.metatrans.commons;


import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.cfg.publishedapp.IPublishedApplication;
import org.metatrans.commons.engagement.ISocialProvider;
import org.metatrans.commons.events.Event_Base;
import org.metatrans.commons.events.api.IEvent_Base;
import org.metatrans.commons.events.api.IEventsManager;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

//import androidx.core.view.WindowCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.core.view.WindowInsetsControllerCompat;


public abstract class Activity_Base extends Activity {
	
	
	private int icon_size;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		//System.out.println("MenuActivity_Base: onCreate()");
		
		super.onCreate(savedInstanceState);
		
		initIconSize();

		Application app = getApplication();

		if (app instanceof Application_Base) {

			((Application_Base) app).getActivitiesStack().onActivity_Create(this);
		}

		// Force edge-to-edge fullscreen
		/*WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

		View decorView = getWindow().getDecorView();
		WindowInsetsControllerCompat insetsController =
				WindowCompat.getInsetsController(getWindow(), decorView);

		if (insetsController != null) {
			// Hide status & nav bars
			insetsController.hide(WindowInsetsCompat.Type.systemBars());
			// Allow bars to show temporarily on swipe
			insetsController.setSystemBarsBehavior(
					WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
		}*/
	}


	@Override
	protected void onDestroy() {

		Application app = getApplication();

		if (app instanceof Application_Base) {

			((Application_Base) getApplication()).getActivitiesStack().onActivity_Destroy(this);
		}

		super.onDestroy();
	}


	@Override
	protected void onResume() {

		super.onResume();

		IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();

		IPublishedApplication app_info = Application_Base.getInstance().getApp_Me();
		String app_name = app_info.getPackage();
		String screen_name = this.getClass().getSimpleName();

		IEvent_Base view_screen_event = new Event_Base(
				IEvent_Base.SCREEN_VIEW, app_name.hashCode(), screen_name.hashCode(),
				"SCREEN_VIEW", app_name, screen_name);

		eventsManager.register(this, view_screen_event);
	}


	@Override
	protected void onPause() {

		super.onPause();
	}


	/*@Override
	public void onStart(){
		
		super.onStart();
		
		((Application_Base)getApplication()).getAnalytics().onActivity_Start(this);
	}
	
	
	@Override
	public void onStop(){
		
		((Application_Base)getApplication()).getAnalytics().onActivity_Stop(this);
		
		super.onStop();
	}*/
	
	
	protected int getBackgroundImageID() {
		return 0;
	}
	
	
	protected void setBackgroundPoster(int layoutID) {
		setBackgroundPoster(layoutID, 77);
	}

	
	protected void setBackgroundPoster(int layoutID, int alpha) {
		View backgroundView = findViewById(layoutID);
		setBackgroundPoster(backgroundView, alpha);
	}
	

	private void setBackgroundPoster(View view, int alpha) {

		if (getBackgroundImageID() != 0) {

			Drawable drawable = getResources().getDrawable(getBackgroundImageID());
			drawable.setAlpha(alpha);

			if (android.os.Build.VERSION.SDK_INT >= 16) {
				view.setBackground(drawable);
			} else {
				view.setBackgroundDrawable(drawable);
			}
		}
	}	
	
	
	protected int getIconSize() {
		return icon_size;
	}
	

	protected void initIconSize() {
		//Rect icon_bounds = getResources().getDrawable(R.drawable.ic_about).getBounds();
		//icon_size = Math.max(icon_bounds.right - icon_bounds.left, icon_bounds.bottom - icon_bounds.top);
		
		int screen_width;
		int screen_height;
		if (android.os.Build.VERSION.SDK_INT >= 13) {
			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			screen_width = size.x;
			screen_height = size.y;
		} else {
			Display display = getWindowManager().getDefaultDisplay(); 
			screen_width = display.getWidth();  // deprecated
			screen_height = display.getHeight();  // deprecated
		}
		
		System.out.println("SCREEN: screen_width=" + screen_width + ", screen_height=" + screen_height);
		
		int factor = 9;
		icon_size = Math.min(screen_width / factor, screen_height / factor);
		icon_size = (int) (1.23 * icon_size);
		
		/*
		BitmapFactory.Options o = new BitmapFactory.Options();
		//o.inTargetDensity = DisplayMetrics.DENSITY_DEFAULT;
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_about, o);
		icon_size = Math.max(bmp.getWidth(), bmp.getHeight());
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		System.out.println("SCREEN DENSITY DPI: " + metrics.densityDpi);
		switch (metrics.densityDpi) {
			case DisplayMetrics.DENSITY_LOW:
				break;
			case DisplayMetrics.DENSITY_MEDIUM:
				break;
			case DisplayMetrics.DENSITY_HIGH:
				break;
			//case DisplayMetrics.DENSITY_DEFAULT:
				//break;
			case DisplayMetrics.DENSITY_TV:
				break;
			case DisplayMetrics.DENSITY_XHIGH:
				break;
			case DisplayMetrics.DENSITY_XXHIGH:
				break;
			default:
				//throw new IllegalStateException("Density=" + metrics.densityDpi);
		}
		*/
		
		System.out.println("ICON SIZE: " + icon_size);
		
		//res/layout/main_activity.xml           # For handsets (smaller than 600dp available width)
		//res/layout-sw600dp/main_activity.xml   # For 7 tablets (600dp wide and bigger)
		//res/layout-sw720dp/main_activity.xml   # For 10 tablets (720dp wide and bigger)
	}
	
	
	/*protected IBitmapCache getBitmapCache() {
		
		return new IBitmapCache() {	
			
			@Override
			public void remove(Integer id) {
			}
			
			
			@Override
			public Bitmap getBitmap(Context context, int imageID) {
				return BitmapUtils.fromResource(Activity_Base.this, imageID);
			}
			
			
			@Override
			public void addBitmap(int imageID, Bitmap bitmap) {
			}
		};
	}*/
	
	
	private Application_Base getApp() {
		return (Application_Base) getApplication();
	}
	
	
	private ISocialProvider getSocialProvider() {

		return getApp().getEngagementProvider().getSocialProvider();
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		System.out.println("Activity_Base: onActivityResult resultCode=" + resultCode);
		
		switch (requestCode) {
		
			case ISocialProvider.REQUEST_CODE_SIGN_IN:
				
				if (resultCode == RESULT_OK) {
					// If the error resolution was successful we should continue
					// processing errors.
					if (getSocialProvider().isConnected()){
						getSocialProvider().setState(ISocialProvider.STATE_SIGNED_IN);
					} else {
						getSocialProvider().connect();
						//getSocialProvider().setState(ISocialProvider.STATE_DEFAULT);
					}
					
				} else if (resultCode == RESULT_CANCELED) {
					
					getSocialProvider().setState(ISocialProvider.STATE_DEFAULT);
					
					getSocialProvider().setSignInRejected(true);
					
				} else {
					
					// If the error resolution was not successful or the user
					// canceled,
					// we should stop processing errors.
					getSocialProvider().setState(ISocialProvider.STATE_ERROR);
					getSocialProvider().setErrorCode(resultCode);
					//getSocialProvider().disconnectAndClear();
					
					/*if (!getSocialProvider().isConnecting() && !getSocialProvider().isConnected()) {
						// If Google Play services resolved the issue with a dialog then
						// onStart is not called so we need to re-attempt connection
						// here.
						getSocialProvider().connect();
					}*/
				}
				
				break;
		}
	}
	
	
	@Override
	protected Dialog onCreateDialog(int id) {
		
		System.out.println("Activity_Base: onCreateDialog id=" + id);
		
		if (id == ISocialProvider.DIALOG_SIGN_IN_ERROR) {
			
			Dialog dialog = getSocialProvider().getErrorDialog();
			
			if (dialog != null) {
				return dialog;
			}
			
		}
		
		return super.onCreateDialog(id);
	}
}
