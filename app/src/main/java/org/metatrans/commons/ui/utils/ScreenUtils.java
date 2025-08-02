package org.metatrans.commons.ui.utils;


//import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Insets;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;

import org.metatrans.commons.app.Application_Base;


public class ScreenUtils {

	private static final int[] SCREEN_SIZE = new int[2];

	static {

		initScreenSize(Application_Base.getInstance());
	}


	public static int[] getScreenSize() {

		return SCREEN_SIZE;
	}


	//@SuppressLint("NewApi")
	public static int[] initScreenSize(Context context) {

		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

		int screenWidth;
		int screenHeight;

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
			// API 30+ (Uses WindowMetrics)
			WindowMetrics windowMetrics = wm.getCurrentWindowMetrics();

			// Exclude system bars using WindowInsets
			Insets insets = windowMetrics.getWindowInsets()
					.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());

			int insetsWidth = insets.left + insets.right;
			int insetsHeight = insets.top + insets.bottom;

			screenWidth = windowMetrics.getBounds().width() - insetsWidth;
			screenHeight = windowMetrics.getBounds().height() - insetsHeight;

		} else if (android.os.Build.VERSION.SDK_INT >= 13) {
			// Legacy for API 13–29
			Display display = wm.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			screenWidth = size.x;
			screenHeight = size.y;
		} else {
			// Very old APIs
			Display display = wm.getDefaultDisplay();
			screenWidth = display.getWidth();
			screenHeight = display.getHeight();
		}

		SCREEN_SIZE[0] = screenWidth;
		SCREEN_SIZE[1] = screenHeight;

		return SCREEN_SIZE;
	}
}
