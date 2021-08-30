package org.metatransapps.commons.ui.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;


public class ScreenUtils {
	
	
	private static final int[] screen_size = new int[2];
	
	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static int[] getScreenSize(Context context) {
		
		//Rect icon_bounds = getResources().getDrawable(R.drawable.ic_about).getBounds();
		//icon_size = Math.max(icon_bounds.right - icon_bounds.left, icon_bounds.bottom - icon_bounds.top);
		WindowManager winman = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		
		int screen_width;
		int screen_height;
		if (android.os.Build.VERSION.SDK_INT >= 13) {
			Display display = winman.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			screen_width = size.x;
			screen_height = size.y;
		} else {
			Display display = winman.getDefaultDisplay(); 
			screen_width = display.getWidth();  // deprecated
			screen_height = display.getHeight();  // deprecated
		}
		
		//System.out.println("SCREEN: screen_width=" + screen_width + ", screen_height=" + screen_height);
		
		screen_size[0] = screen_width;
		screen_size[1] = screen_height;
		
		//DisplayMetrics metrics = new DisplayMetrics();
		//activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		//System.out.println("SCREEN DENSITY DPI (l/m/h=120/160/320): " + metrics.densityDpi);
		//System.out.println("ICON SIZE: " + icon_size);
		
		//res/layout/main_activity.xml           # For handsets (smaller than 600dp available width)
		//res/layout-sw600dp/main_activity.xml   # For 7 tablets (600dp wide and bigger)
		//res/layout-sw720dp/main_activity.xml   # For 10 tablets (720dp wide and bigger)
		
		return screen_size;
	}
}
