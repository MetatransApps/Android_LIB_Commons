package org.metatrans.commons.ui.utils;


import android.app.Activity;
import android.content.Context;
import android.graphics.Insets;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.os.Build;

import org.metatrans.commons.app.Application_Base;


public class ScreenUtils {

	private static final int[] SCREEN_SIZE_WITH_BARS 	= new int[2];
	private static final int[] SCREEN_SIZE 				= new int[2];

	private static boolean SCREEN_SIZE_INITIALIZED 		= false;


	static {

		Context context = Application_Base.getInstance();
		if (Application_Base.getInstance().getCurrentActivity() != null) {
			context = Application_Base.getInstance().getCurrentActivity();
		}

		initScreenSize(context, SCREEN_SIZE_WITH_BARS);
	}


	public static int[] getScreenSize(Context context) {

		if (!SCREEN_SIZE_INITIALIZED) {

			initScreenSize(context, SCREEN_SIZE);

			SCREEN_SIZE_INITIALIZED = true;
		}

		return SCREEN_SIZE;
	}


	public static int[] getScreenSize_WithBars() {

		return SCREEN_SIZE_WITH_BARS;
	}


	public static void initScreenSize(Context context, int[] output) {

		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

		int screenWidth;
		int screenHeight;

		// API 30+ (Android 11+)
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

			if (context instanceof Activity) {
				// Use WindowMetrics + Insets for Activity (accurate)
				WindowMetrics metrics = wm.getCurrentWindowMetrics();
				Insets insets = metrics.getWindowInsets()
						.getInsets(WindowInsets.Type.systemBars());

				int width = metrics.getBounds().width();
				int height = metrics.getBounds().height();

				screenWidth = width - insets.left - insets.right;
				screenHeight = height /*- insets.top*/ - insets.bottom;

			} else {

				// Application context: Use DisplayMetrics + subtract system bars manually
				DisplayMetrics metrics = context.getResources().getDisplayMetrics();
				int statusBarHeight = getStatusBarHeight(context);
				int navBarHeight = getNavigationBarHeight(context);
				screenWidth = metrics.widthPixels;
				screenHeight = metrics.heightPixels /*- statusBarHeight*/ - navBarHeight;
			}
		}
		// API 13–29
		else if (Build.VERSION.SDK_INT >= 13) {
			Display display = wm.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			screenWidth = size.x;
			screenHeight = size.y;
		}
		// API < 13
		else {
			Display display = wm.getDefaultDisplay();
			screenWidth = display.getWidth();
			screenHeight = display.getHeight();
		}

		output[0] = screenWidth;
		output[1] = screenHeight;
	}

	private static int getStatusBarHeight(Context context) {
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		return (resourceId > 0) ? context.getResources().getDimensionPixelSize(resourceId) : 0;
	}

	private static int getNavigationBarHeight(Context context) {
		int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
		return (resourceId > 0) ? context.getResources().getDimensionPixelSize(resourceId) : 0;
	}
}

