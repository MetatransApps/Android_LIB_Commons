package com.apps.mobile.android.commons.engagement.social;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import com.apps.mobile.android.commons.R;
import com.apps.mobile.android.commons.app.Application_Base;
import com.apps.mobile.android.commons.cfg.colours.IConfigurationColours;
import com.apps.mobile.android.commons.engagement.ISocialProvider;
import com.apps.mobile.android.commons.events.api.IEvent_Base;
import com.apps.mobile.android.commons.events.api.IEventsManager;


public class SocialProvider_Dummy implements ISocialProvider {
	  
	  
	private Application_Base app;
	private boolean connected;
	
	
	public SocialProvider_Dummy(Application_Base _app) {
		
		app = _app;
		
		connect();
	}
	
	
	@Override
	public boolean isSignInRejected() {
		return false;
	}
	
	
	@Override
	public void setSignInRejected(boolean rejected) {
		
	}
	
	@Override
	public void connect() {
		connected = true;
	}

	@Override
	public void disconnect() {
		connected = false;
	}
	
	@Override
	public void disconnectAndClear() {
		connected = false;
	}
	
	@Override
	public boolean isConnecting() {
		return false;
	}

	@Override
	public boolean isConnected() {
		return connected;
	}

	@Override
	public int getState() {
		if (connected) {
			return ISocialProvider.STATE_SIGNED_IN;
		} else {
			return ISocialProvider.STATE_DEFAULT;	
		}
	}

	@Override
	public void setState(int state) {
	}

	@Override
	public String getStateMesage() {
		return null;
	}

	@Override
	public int getErrorCode() {
		return 0;
	}

	@Override
	public void setErrorCode(int code) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Dialog getErrorDialog() {
		return null;
	}

	@Override
	public Bitmap getUserIcon() {
		return null;
	}
	
	
	/*@Override
	public void submitLeaderboardScore(int modeID, long score) {
		app.getEngagementProvider().getLeaderboardsProvider().submitLeaderboardScore(modeID, score);
	}
	
	
	@Override
	public void openLeaderboard(int modeID) {
		app.getEngagementProvider().getLeaderboardsProvider().openLeaderboard(modeID);
	}*/
	
	
	@Override
	public void openInviteDialog() {
		
		if (isConnected()) {
			
			String message = app.getString(app.getApp_Me().getName())
					+ " "
					+ app.getString(R.string.social_invite_isprettycool)
					+ " "
					+ app.getString(R.string.social_invite_checkitout)
					//+ "\r\n"
					+ "\r\n"
					+ Uri.parse(app.getApp_Me().getMarketURL())
					+ "\r\n";
			
			
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setType("text/plain");
			
			shareIntent.putExtra(Intent.EXTRA_SUBJECT,
					app.getString(R.string.social_invite_invitationto)
					+ " "
					+ app.getString(app.getApp_Me().getName()));
			
			shareIntent.putExtra(Intent.EXTRA_TEXT, message);
			
			//shareIntent.putExtra(Intent.EXTRA_EMAIL, toEmails);
			
			//context.startActivity(Intent.createChooser(shareIntent, fileChooserName));
			
			
			/*Intent shareIntent = new PlusShare.Builder(app)
		          .setType("text/plain")
		          .setText(message)
		          .setContentUrl(Uri.parse(app.getApp_Me().getMarketURL()))
		          .getIntent();
		      */
			
			if (app.getCurrentActivity() != null) {
				app.getCurrentActivity().startActivityForResult(Intent.createChooser(shareIntent, "Share via"), ISocialProvider.REQUEST_CODE_INVITE);
				
	        	IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
	    		eventsManager.register(app,
	    				eventsManager.create(IEvent_Base.MARKETING, IEvent_Base.MARKETING_INVITE_FRIENDS_CLICKED,
	    				"MARKETING", "INVITE_FRIENDS_CLICKED"));
			}
		      
		} else {
			
			//Toast_Base.showToast_InCenter_Long(app, app.getString(com.apps.mobile.android.commons.R.string.label_leaderboards_not_available));
			
			connect();
			
		}
		
	}



	@Override
	public View getSignInView(IConfigurationColours coloursCfg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void detachSignInView(ViewGroup frame) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public View getLikeView(IConfigurationColours coloursCfg, RectF rectf) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void detachLikeView(ViewGroup frame) {
		// TODO Auto-generated method stub
		
	}
}
