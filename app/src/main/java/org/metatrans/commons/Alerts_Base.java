package org.metatrans.commons;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import org.metatrans.commons.app.Application_Base;


public class Alerts_Base {
	
	
	protected static final OnClickListener listener_empty = new DialogInterface.OnClickListener() {

		public void onClick(DialogInterface dialog, int which) {

			Application_Base.getInstance().getSFXManager().playSound(R.raw.sfx_button_pressed_1);

			dialog.dismiss();
		}
	};
	
	
	public static AlertDialog.Builder createAlertDialog_LoseGame(Context context, OnClickListener positive) {
		return createAlertDialog_LoseGame(context, positive, listener_empty);
	}
	
	
	public static AlertDialog.Builder createAlertDialog_LowerDifficulty(Context context, OnClickListener positive) {
		return createAlertDialog_LowerDifficulty(context, positive, listener_empty);
	}
	
	
	public static AlertDialog.Builder createAlertDialog_Exit(Context context, OnClickListener positive) {
		return createAlertDialog_Exit(context, positive, listener_empty);
	}
	
	
	public static AlertDialog.Builder createAlertDialog_LoseGame(Context context, OnClickListener positive, OnClickListener negative) {
		return createAlertDialog(context, positive, negative, null, R.string.alert_message_newgame);
	}


	public static AlertDialog.Builder createAlertDialog_OverrideMoveSequence(Context context, OnClickListener positive, OnClickListener negative, DialogInterface.OnCancelListener onCancelListener, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {

		View checkBoxView = createCheckBoxInView(context, R.string.dont_ask_again, onCheckedChangeListener);

		return createAlertDialog(context, positive, negative, onCancelListener, R.string.alert_message_newmove, checkBoxView);
	}

	public static AlertDialog.Builder createAlertDialog_RateApp(Context context, OnClickListener positive, OnClickListener negative, DialogInterface.OnCancelListener onCancelListener) {

		return createAlertDialog_RateApp(context, positive, negative, onCancelListener, null);
	}


	public static AlertDialog.Builder createAlertDialog_RateApp(Context context, OnClickListener positive, OnClickListener negative,
																DialogInterface.OnCancelListener onCancelListener,
																CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {



		AlertDialog.Builder adb = new AlertDialog.Builder(context);

		adb.setIcon(R.drawable.ic_star_gold_v2_64);
		adb.setTitle(R.string.alert_message_rateapp_needs_review);
		adb.setMessage(R.string.alert_message_rateapp_long_text);
		adb.setPositiveButton(R.string.ok, positive);
		adb.setNegativeButton(R.string.not_now, negative);

		if (onCancelListener != null) {

			adb.setOnCancelListener(onCancelListener);
		}

		if (onCheckedChangeListener != null) {
			View checkBoxView = createCheckBoxInView(context, R.string.dont_ask_again, onCheckedChangeListener);
			adb.setView(checkBoxView);
		}
		//adb.setCancelable(false);

		return adb;
	}

	protected static AlertDialog.Builder createAlertDialog_LowerDifficulty(Context context, OnClickListener positive, OnClickListener negative) {
		return createAlertDialog(context, positive, negative, null, R.string.alert_message_lower_difficulty);
	}
	
	
	protected static AlertDialog.Builder createAlertDialog_Exit(Context context, OnClickListener positive, OnClickListener negative) {
		return createAlertDialog(context, positive, negative, null, R.string.alert_message_exit);
	}
	
	
	public static AlertDialog.Builder createAlertDialog_NotEnoughMemory(Context context, OnClickListener negative, String message) {
		return createAlertDialog_EXIT(context, negative, message);
	}


	public static AlertDialog.Builder createAlertDialog_LowMemory(Context context, OnClickListener negative, String message) {
		return createAlertDialog_Continue(context, negative, message);
	}


	protected static AlertDialog.Builder createAlertDialog(Context context, OnClickListener positive, OnClickListener negative, DialogInterface.OnCancelListener onCancelListener, int messageID) {

		return createAlertDialog(context, positive, negative, onCancelListener, messageID, null);
	}


	protected static AlertDialog.Builder createAlertDialog(Context context, OnClickListener positive, OnClickListener negative, DialogInterface.OnCancelListener onCancelListener, int messageID, View checkBoxView) {

		AlertDialog.Builder adb = new AlertDialog.Builder(context);

		adb.setIcon(android.R.drawable.ic_dialog_alert);
		adb.setTitle(R.string.alert_title);
		adb.setMessage(messageID);
		adb.setPositiveButton(R.string.yes, positive);
		adb.setNegativeButton(R.string.no, negative);
		if (checkBoxView != null) {
			adb.setView(checkBoxView);
		}
		//adb.setCancelable(false);

		if (onCancelListener != null) {

			adb.setOnCancelListener(onCancelListener);
		}

		return adb;
	}


	private static View createCheckBoxInView(Context context, int text_id, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {

		View checkBoxView = View.inflate(context, R.layout.checkbox, null);

		CheckBox checkBox = (CheckBox) checkBoxView.findViewById(R.id.checkbox);

		checkBox.setOnCheckedChangeListener(onCheckedChangeListener);

		checkBox.setText(text_id);

		return checkBoxView;
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
