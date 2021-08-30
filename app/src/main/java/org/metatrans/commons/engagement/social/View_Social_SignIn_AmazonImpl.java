package org.metatrans.commons.engagement.social;


import org.metatrans.commons.R;
import org.metatrans.commons.cfg.colours.IConfigurationColours;
import org.metatrans.commons.engagement.ISocialProvider;

import android.content.Context;


public class View_Social_SignIn_AmazonImpl extends View_Social_SignIn_Base {
	
	
	public View_Social_SignIn_AmazonImpl(Context context, ISocialProvider _provider, IConfigurationColours _coloursCfg) {
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
