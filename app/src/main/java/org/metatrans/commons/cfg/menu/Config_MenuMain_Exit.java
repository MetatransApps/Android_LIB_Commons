package org.metatrans.commons.cfg.menu;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import org.metatrans.commons.Alerts_Base;
import org.metatrans.commons.R;
import org.metatrans.commons.app.Application_Base;


public class Config_MenuMain_Exit extends Config_MenuMain_Base {


	@Override
	public int getName() {
		return R.string.exit;
	}

	@Override
	public int getIconResID() {
		return R.drawable.ic_action_halt_white;
	}

	@Override
	public int getID() {
		return CFG_MENU_EXIT;
	}

	@Override
	public String getDescription_String() {
		return "";
	}

	@Override
	public Runnable getAction() {

		return new Runnable() {

			@Override
			public void run() {

				//Exit
				Activity current_activity = Application_Base.getInstance().getCurrentActivity();

				AlertDialog.Builder adb = Alerts_Base.createAlertDialog_Exit(current_activity,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
								current_activity.finish();
								Intent homeIntent = new Intent(Intent.ACTION_MAIN);
								homeIntent.addCategory(Intent.CATEGORY_HOME);
								homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								current_activity.startActivity(homeIntent);
								//System.exit(0);
							}
						});

				adb.show();

			}
		};
	}
}
