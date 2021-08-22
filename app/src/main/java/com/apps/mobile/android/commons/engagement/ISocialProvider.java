package com.apps.mobile.android.commons.engagement;


import com.apps.mobile.android.commons.cfg.colours.IConfigurationColours;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;


public interface ISocialProvider {
	
	
	public static final int DIALOG_SIGN_IN_ERROR = 0;
	
	public static int REQUEST_CODE_SIGN_IN = 1234567;
	public static int REQUEST_CODE_INVITE = 34255768;
	
	public static final int STATE_DEFAULT 		= 0;
	public static final int STATE_IN_PROGRESS 	= 2;
	public static final int STATE_ERROR 		= 3;
	public static final int STATE_SIGNED_IN 	= 4;
	
	
	public View getSignInView(IConfigurationColours coloursCfg);
	public void detachSignInView(ViewGroup frame);
	
	public View getLikeView(IConfigurationColours coloursCfg, RectF rectf);
	public void detachLikeView(ViewGroup frame);
	
	public boolean isSignInRejected();
	public void setSignInRejected(boolean rejected);
	
	public void connect();
	public void disconnect();
	public void disconnectAndClear();
	
	public boolean isConnecting();
	public boolean isConnected();
	
	public int getState();
	public void setState(int state);
	public String getStateMesage();
	
	public int getErrorCode();
	public void setErrorCode(int code);
	public Dialog getErrorDialog();
	
	public Bitmap getUserIcon();
	
	public void openInviteDialog();
}
