package com.apps.mobile.android.commons.engagement.leaderboards;


import android.app.Activity;
import android.content.Intent;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;

import com.apps.mobile.android.commons.app.Application_Base;
import com.apps.mobile.android.commons.cfg.colours.IConfigurationColours;
import com.apps.mobile.android.commons.engagement.ILeaderboardsProvider;
import com.apps.mobile.android.commons.engagement.achievements.Activity_Scores;


public class LeaderboardsProvider_Base_Scores implements ILeaderboardsProvider {
	
	
	private static final int VIEWID_LEADERBOARDS = 452308978;
	  
	
	private Application_Base app;
	private View view_leaderboard;
	
	
	public LeaderboardsProvider_Base_Scores(Application_Base _app) {
		app = _app;
	}
	
	
	@Override
	public void submitLeaderboardScore(int modeID, long score) {
		//Do nothing, it is already store in local best scores
	}
	
	
	@Override
	public void openLeaderboard(int modeID) {
		
    	Activity currentActivity = app.getCurrentActivity();
    	
		if (currentActivity != null) {
			Intent intent = new Intent(currentActivity, Activity_Scores.class);
			currentActivity.startActivity(intent);
		}
	}
	
	
	@Override
	public void openLeaderboard_LocalOnly(int modeID) {
		//Do Nothing
	}
	
	
	@Override
	public View getLeaderboardView(IConfigurationColours coloursCfg, RectF rectf) {
		
		return getLeaderboardView(coloursCfg, rectf, new ViewActivator_Enabled());
	}
	
	
	@Override
	public View getLeaderboardView(IConfigurationColours coloursCfg, RectF rectf, IViewActivator activator) {
		
		if (view_leaderboard == null) {
			
			view_leaderboard = new View_Achievements_And_Leaderboards_Offline(app, rectf, app.getEngagementProvider(), coloursCfg, activator);
			view_leaderboard.setId(VIEWID_LEADERBOARDS);
			
		}
		
		return view_leaderboard;
	}
	
	
	@Override
	public void detachLeaderboardView(ViewGroup frame) {

		detachView(frame, VIEWID_LEADERBOARDS);
		
		view_leaderboard = null;
	}
	
	
	private void detachView(ViewGroup frame, int viewid) {
		if (frame != null) {
			View old = frame.findViewById(viewid);
			if (old != null) {
				frame.removeView(old);
			}
		}
	}


	@Override
	public void setEnabled(boolean _enabled) {
		//Do Nothing
	}
}
