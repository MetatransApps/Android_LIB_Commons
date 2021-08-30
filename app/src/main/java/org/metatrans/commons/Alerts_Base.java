package org.metatrans.commons;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;


public class Alerts_Base {
	
	
	protected static final OnClickListener listener_empty = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
		}
	};
	
	
	public static AlertDialog.Builder createAlertDialog_LoseGame(Context context, OnClickListener possitive) {
		return createAlertDialog_LoseGame(context, possitive, listener_empty);
	}
	
	
	public static AlertDialog.Builder createAlertDialog_LowerDifficulty(Context context, OnClickListener possitive) {
		return createAlertDialog_LowerDifficulty(context, possitive, listener_empty);
	}
	
	
	public static AlertDialog.Builder createAlertDialog_Exit(Context context, OnClickListener possitive) {
		return createAlertDialog_Exit(context, possitive, listener_empty);
	}
	
	
	public static AlertDialog.Builder createAlertDialog_LoseGame(Context context, OnClickListener possitive, OnClickListener negative) {
		return createAlertDialog(context, possitive, negative, R.string.alert_message_newgame);
	}
	
	
	protected static AlertDialog.Builder createAlertDialog_LowerDifficulty(Context context, OnClickListener possitive, OnClickListener negative) {
		return createAlertDialog(context, possitive, negative, R.string.alert_message_lower_difficulty);
	}
	
	
	protected static AlertDialog.Builder createAlertDialog_Exit(Context context, OnClickListener possitive, OnClickListener negative) {
		return createAlertDialog(context, possitive, negative, R.string.alert_message_exit);
	}
	
	
	public static AlertDialog.Builder createAlertDialog_NotEnoughMemory(Context context, OnClickListener negative, String message) {
		return createAlertDialog_EXIT(context, negative, message);
	}

	public static AlertDialog.Builder createAlertDialog_LowMemory(Context context, OnClickListener negative, String message) {
		return createAlertDialog_Continue(context, negative, message);
	}
	
	protected static AlertDialog.Builder createAlertDialog(Context context, OnClickListener possitive, OnClickListener negative, int messageID) {
		
		AlertDialog.Builder adb = new AlertDialog.Builder(context);
		
		//adb.setView(list);
		adb.setIcon(android.R.drawable.ic_dialog_alert);
		adb.setTitle(R.string.alert_title);
		adb.setMessage(messageID);
		adb.setPositiveButton(R.string.yes, possitive);
		adb.setNegativeButton(R.string.no, negative);
		
		return adb;
	}
	
	
	public static AlertDialog.Builder createAlertDialog_OK(Context context, OnClickListener neutral, String message) {
		return createAlertDialog_SingleButton(context, neutral, message, R.string.ok);
	}

	
	protected static AlertDialog.Builder createAlertDialog_EXIT(Context context, OnClickListener neutral, String message) {
		return createAlertDialog_SingleButton(context, neutral, message, R.string.exit);
	}
	
	
	protected static AlertDialog.Builder createAlertDialog_Continue(Context context, OnClickListener neutral, String message) {
		return createAlertDialog_SingleButton(context, neutral, message, R.string.label_continue);
	}


	protected static AlertDialog.Builder createAlertDialog_SingleButton(Context context, OnClickListener neutral, String message, int buttonTextID) {
		AlertDialog.Builder adb = new AlertDialog.Builder(context);
		
		adb.setIcon(android.R.drawable.ic_dialog_alert);
		adb.setTitle(R.string.alert_title);
		adb.setMessage(message);
		adb.setNeutralButton(buttonTextID, neutral);
		
		return adb;
	}
}
