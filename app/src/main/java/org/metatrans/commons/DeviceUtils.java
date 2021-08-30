package org.metatrans.commons;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.metatrans.commons.app.Application_Base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;


public class DeviceUtils {
	
	
	private static long JVM_MEM_USAGE = 3 * 1024 * 1024;//7 * 1024 * 1024;
	
	
	public static boolean isConnected() {
		boolean isConnected = false;
		ConnectivityManager cm = (ConnectivityManager)Application_Base.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm != null) {
			NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
			isConnected = activeNetwork != null && activeNetwork.isConnected();
		}
		//System.out.println("DeviceUtils: isConnected=" + isConnected);
		return isConnected;
	}
	
	
	public static boolean isConnectedOrConnecting() {
		boolean isConnected = false;
		ConnectivityManager cm = (ConnectivityManager)Application_Base.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm != null) {
			NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
			isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
		}
		//System.out.println("DeviceUtils: isConnected=" + isConnected);
		return isConnected;
	}
	
	
	public static long getJVM_MemoryUsage() {
		return JVM_MEM_USAGE;
	}
	
	
	public static int getJVM_MemoryUsage_InMB() {
		return (int) (getJVM_MemoryUsage() / (1024 * 1024));
	}
	
	
	public static long getAvailableMemory_InMB() {
		return getAvailableMemory() / (1024 * 1024);
	}
	
	
	public static long getAvailableMemory() {
		
		System.gc();
		
		long max_mem = (int) Runtime.getRuntime().maxMemory();
		
		long total_mem = (int) Runtime.getRuntime().totalMemory();
		long free_mem = (int) Runtime.getRuntime().freeMemory();
		long used_mem = total_mem - free_mem;
		
		long available_mem = max_mem - used_mem;
		
		return available_mem - getJVM_MemoryUsage();
	}
	
	
	public static String getDeviceID() {
		String android_id = Settings.Secure.getString(Application_Base.getInstance().getContentResolver(), Settings.Secure.ANDROID_ID);
		String deviceId = md5(android_id).toUpperCase();
		return deviceId;
	}
	
	
	private static final String md5(final String s) {
	    try {
	        // Create MD5 Hash
	        MessageDigest digest = java.security.MessageDigest
	                .getInstance("MD5");
	        digest.update(s.getBytes());
	        byte messageDigest[] = digest.digest();

	        // Create Hex String
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < messageDigest.length; i++) {
	            String h = Integer.toHexString(0xFF & messageDigest[i]);
	            while (h.length() < 2)
	                h = "0" + h;
	            hexString.append(h);
	        }
	        return hexString.toString();

	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    
	    return "";
	}
}
