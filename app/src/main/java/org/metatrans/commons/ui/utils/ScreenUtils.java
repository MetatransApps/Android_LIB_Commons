package org.metatrans.commons.ui.utils;


import android.content.Context;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.os.Build;

import org.metatrans.commons.app.Application_Base;


public class ScreenUtils {


	private static final int[] SCREEN_SIZE_WITH_BARS 	= new int[2];


	static {

		initScreenSize(Application_Base.getInstance(), SCREEN_SIZE_WITH_BARS);
	}


	public static int[] getScreenSize(Context context) {

		return SCREEN_SIZE_WITH_BARS;
	}


	public static void initScreenSize(Context context, int[] output) {

		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

		int screenWidth;
		int screenHeight;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

			WindowMetrics metrics = wm.getCurrentWindowMetrics();
			Rect bounds = metrics.getBounds();

			Insets visibleInsets = metrics.getWindowInsets()
					.getInsets(
							WindowInsets.Type.displayCutout()
					);

			screenWidth = bounds.width() - (visibleInsets.left + visibleInsets.right);
			screenHeight = bounds.height() - (visibleInsets.top + visibleInsets.bottom);
		}
		else if (Build.VERSION.SDK_INT >= 13) {

			// Legacy for API 13–29
			Display display = wm.getDefaultDisplay();
			Point size = new Point();
			display.getRealSize(size); // real size includes system bars
			screenWidth = size.x;
			screenHeight = size.y;
		}
		else {

			// Very old APIs
			Display display = wm.getDefaultDisplay();
			screenWidth = display.getWidth();
			screenHeight = display.getHeight();
		}

		output[0] = screenWidth;
		output[1] = screenHeight;
	}
}

