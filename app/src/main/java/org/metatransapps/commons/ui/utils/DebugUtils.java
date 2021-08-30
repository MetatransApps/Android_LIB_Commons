package org.metatransapps.commons.ui.utils;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;


public class DebugUtils {
	
	
	public static boolean isDebuggable(Context ctx)
	{
	    boolean debuggable = false;
	 
	    PackageManager pm = ctx.getPackageManager();
	    try
	    {
	        ApplicationInfo appinfo = pm.getApplicationInfo(ctx.getPackageName(), 0);
	        debuggable = (0 != (appinfo.flags &= ApplicationInfo.FLAG_DEBUGGABLE));
	    }
	    catch(NameNotFoundException e)
	    {
	        /*debuggable variable will remain false*/
	    }
	     
	    return debuggable;
	}
}
