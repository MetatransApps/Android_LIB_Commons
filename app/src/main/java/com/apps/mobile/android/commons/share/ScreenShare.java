package com.apps.mobile.android.commons.share;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.apps.mobile.android.commons.ui.Toast_Base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;


public class ScreenShare {
	
	
	public static Bitmap getScreen(Context context, View view) {
		
		Toast_Base.showToast_InCenter_Short(context, "Take picture of the screen ...");
		
		View v = view.getRootView();
		v.setDrawingCacheEnabled(true);
		v.buildDrawingCache(true);
		
		Bitmap bitmap = v.getDrawingCache();
		
		return bitmap;
	}
	
	
	public static void cleanup(Context context, View view) {
		view.destroyDrawingCache();
	}
	
	
	public static void share(Context context, String root, String fileName, Bitmap bitmap, String fileChooserName, String subject, String text, String[] toEmails) {
		
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("image/jpeg");
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		boolean compressed = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
		
        if (compressed) {
            
        	Toast_Base.showToast_InCenter_Short(context, "Succeeded to take picture of the screen.");
    		
        	String state = Environment.getExternalStorageState();
        	
            if (Environment.MEDIA_MOUNTED.equals(state)) {
    		
            	Toast_Base.showToast_InCenter_Short(context, "Saving screen image to SD card ...");
            	
        		FileOutputStream fo = null;
        		
    			try {
    		
    				boolean dirOK = true;
    				File dir = new File(root);
    				if (!dir.exists()) {
    					dirOK = dir.mkdirs();
    				}
    				
    				if (dirOK) {
	    				File f = new File(root + File.separator + fileName);
	    			    f.createNewFile();
	    			    fo = new FileOutputStream(f);
	    			    fo.write(bytes.toByteArray());
	        			
	        			Toast_Base.showToast_InCenter_Short(context, "Screen image saved to SD card under " +  f.getAbsolutePath());
	        			
	        			share.putExtra(Intent.EXTRA_STREAM, Uri.parse(root +  File.separator + fileName));
	        			share.putExtra(Intent.EXTRA_SUBJECT, subject);
	        			share.putExtra(Intent.EXTRA_TEXT, text);
	        			share.putExtra(Intent.EXTRA_EMAIL, toEmails);
	        			
	        			context.startActivity(Intent.createChooser(share, fileChooserName));
	        			
    				} else {
    					Toast_Base.showToast_InCenter_Short(context, "Cannot create directory on SD card " + root);
    				}
    			} catch (IOException e) {                       
    				
    			        e.printStackTrace();
    			        
    			        Toast_Base.showToast_InCenter_Short(context, "Screen image CANNOT be saved to SD card - I/O error occured.");
    			        
    			} finally {
    				if (fo != null) { 
    					try {
    						fo.close();
    					} catch (IOException e) {
    					}
    				}
    			}
    			
            } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            	
            	Toast_Base.showToast_InCenter_Short(context, "Screen image CANNOT be saved to SD card - media is read only.");
            	
            } else {
            	
            	Toast_Base.showToast_InCenter_Short(context, "Screen image CANNOT be saved to SD card - media not mounted.");
            	
            }
            
        } else {
            //Error
        	Toast_Base.showToast_InCenter_Short(context, "Failed to take picture of the screen.");
        }
        
		if (bytes != null) { 
			try {
				bytes.close();
			} catch (IOException e) {
			}
		}
	}
}
