package org.metatrans.commons.web;


import org.metatrans.commons.DeviceUtils;
import org.metatrans.commons.R;
import org.metatrans.commons.cfg.appstore.IAppStore;
import org.metatrans.commons.cfg.publishedapp.IPublishedApplication;
import org.metatrans.commons.ui.Toast_Base;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;


public class WebUtils {
	
	
	public static final boolean openApplicationStorePage(Context parent, IPublishedApplication app) {
		return openApplicationStorePage(parent, app, false /*true*/ );
	}
	
	
	public static final boolean openApplicationStorePage(Context parent, IPublishedApplication app, boolean checkConnection) {
		
		
		if (checkConnection) {
			if (!DeviceUtils.isConnected()) {
				Toast_Base.showToast_InCenter_Short(parent, "  " + parent.getString(R.string.label_noconnection) + "  ");
				return false;
			}
		}
		
    	
    	boolean openned = false;
    	
		if (app.getAppStore().getID() == IAppStore.ID_GOOGLE) {
			
			try {
				
				parent.startActivity(getViewIntent(Uri.parse("market://details?id=" + app.getPackage())));
				openned = true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} /*else if (app.getAppStore().getID() == IAppStore.ID_SAMSUNG) {
			
			try {
				
				parent.startActivity(getViewIntent(Uri.parse("samsungapps://ProductDetail/" + app.getPackage())));
				openned = true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (app.getAppStore().getID() == IAppStore.ID_AMAZON) {
			
			try {
				
				parent.startActivity(getViewIntent(Uri.parse("amzn://apps/android?p=" + app.getPackage())));
				openned = true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (app.getAppStore().getID() == IAppStore.ID_YANDEX) {
			
			try {
				
				parent.startActivity(getViewIntent(Uri.parse("yastore://details?id=" + app.getPackage())));
				openned = true;
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
				//Try Google like
				try {
					
					parent.startActivity(getViewIntent(Uri.parse("market://details?id=" + app.getPackage())));
					openned = true;
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}*/
		
		
		//Standard flow
		if (!openned) {
			
	    	Intent intent = new Intent(parent, Activity_WebView_StatePreservingImpl_With_VideoPlayer.class);
	    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
	    	intent.putExtra("URL", app.getMarketURL());
	    	intent.putExtra("titleID", app.getName());
	    	parent.startActivity(intent);
	    	
	    	return true;
	    	
		} else {
			
			return true;
		}
	}


	private static Intent getViewIntent(Uri uri) {
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		return intent;
	}
	
}
