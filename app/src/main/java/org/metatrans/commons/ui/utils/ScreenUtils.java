package org.metatrans.commons.ui.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import org.metatrans.commons.app.Application_Base;


public class ScreenUtils {

	private static final int[] SCREEN_SIZE = new int[2];

	static {

		initScreenSize(Application_Base.getInstance());
	}


	public static int[] getScreenSize(/*Context context*/) {

		return SCREEN_SIZE;
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static int[] initScreenSize(Context context) {

		//Rect icon_bounds = getResources().getDrawable(R.drawable.ic_about).getBounds();
		//icon_size = Math.max(icon_bounds.right - icon_bounds.left, icon_bounds.bottom - icon_bounds.top);
		WindowManager winman = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

		float density = 1; //context.getResources().getDisplayMetrics().density;

		int screen_width;
		int screen_height;

		if (android.os.Build.VERSION.SDK_INT >= 13) {

			Display display = winman.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			screen_width = (int) (size.x / density);
			screen_height = (int) (size.y / density);

		} else {

			Display display = winman.getDefaultDisplay(); 
			screen_width = (int) (display.getWidth() / density);  // deprecated
			screen_height = (int) (display.getHeight() / density);  // deprecated
		}

		//System.out.println("SCREEN: screen_width=" + screen_width + ", screen_height=" + screen_height);

		SCREEN_SIZE[0] = screen_width;
		SCREEN_SIZE[1] = screen_height;

		return SCREEN_SIZE;
	}
}
