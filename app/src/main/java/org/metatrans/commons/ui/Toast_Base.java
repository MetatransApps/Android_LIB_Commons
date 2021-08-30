package org.metatrans.commons.ui;


import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;


public class Toast_Base {

	
	private static Toast toast;
	
	
	public static void showToast_InCenter(Context context, String info) {
		
		if (toast != null) {
			toast.cancel();
		}
		
		toast = Toast.makeText(context, info, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		//toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
        
		toast.show();
        
	}
	
	
	public static void showToast_InCenter_Short(Context context, String info) {
		
		if (toast != null) {
			toast.cancel();
		}
		
		toast = Toast.makeText(context, info, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		//toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
        
		toast.show();
        
	}
	
	
	public static void showToast_InCenter_Long(Context context, String info) {
		
		if (toast != null) {
			toast.cancel();
		}
		
		toast = Toast.makeText(context, info, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		//toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
        
		toast.show();
        
	}
	
	
	public static void showToast_InBottom_Long(Context context, String info) {
		
		if (toast != null) {
			toast.cancel();
		}
		
		toast = Toast.makeText(context, info, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
		//toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
        
		toast.show();
        
	}
	
	
	public static void showToast_InTop_Long(Context context, String info) {
		
		if (toast != null) {
			toast.cancel();
		}
		
		toast = Toast.makeText(context, info, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
		//toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
        
		toast.show();
        
	}
}
