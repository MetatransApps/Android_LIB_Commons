package com.apps.mobile.android.commons.engagement.social;


import android.content.Context;

import com.apps.mobile.android.commons.R;
import com.apps.mobile.android.commons.cfg.colours.IConfigurationColours;
import com.apps.mobile.android.commons.engagement.ISocialProvider;


public class View_Social_SignIn_GoogleImpl extends View_Social_SignIn_Base {
	
	
	public View_Social_SignIn_GoogleImpl(Context context, ISocialProvider _provider, IConfigurationColours _coloursCfg) {
		super(context, _provider, _coloursCfg);
	}
	
	
	@Override
	public int getResID_Button_SignIn() {
		return R.drawable.btn_google_plus_signin;
	}


	@Override
	public int getResID_Button_SignOut() {
		return R.drawable.btn_google_plus_signout;
	}

	
	@Override
	public int getResID_Button_InProgress() {
		return R.drawable.btn_google_plus_signingin;
	}
}
