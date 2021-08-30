package org.metatransapps.commons.engagement;


import org.metatransapps.commons.cfg.colours.IConfigurationColours;
import org.metatransapps.commons.engagement.leaderboards.IViewActivator;

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
