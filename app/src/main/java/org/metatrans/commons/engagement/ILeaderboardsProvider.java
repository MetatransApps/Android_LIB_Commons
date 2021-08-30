package org.metatrans.commons.engagement;


import org.metatrans.commons.cfg.colours.IConfigurationColours;
import org.metatrans.commons.engagement.leaderboards.IViewActivator;

import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;


public interface ILeaderboardsProvider {
	
	public void submitLeaderboardScore(int modeID, long score);
	
	public void openLeaderboard(int modeID);
	
	public void openLeaderboard_LocalOnly(int modeID);
	
	public View getLeaderboardView(IConfigurationColours coloursCfg, RectF rectf);
	public View getLeaderboardView(IConfigurationColours coloursCfg, RectF rectf, IViewActivator activator);
	
	public void detachLeaderboardView(ViewGroup frame);
	
	public void setEnabled(boolean enabled);
}
