package org.metatransapps.commons.engagement.leaderboards;


import org.metatransapps.commons.R;
import org.metatransapps.commons.cfg.colours.IConfigurationColours;
import org.metatransapps.commons.engagement.IEngagementProvider;

import android.content.Context;
import android.graphics.RectF;


public class View_Achievements_And_Leaderboards_GoogleImpl extends View_Achievements_And_Leaderboards_Base {
	
	
	public View_Achievements_And_Leaderboards_GoogleImpl(Context context, RectF _rectf_main, IEngagementProvider provider, IConfigurationColours _coloursCfg, IViewActivator activator) {
		super(context, _rectf_main, provider, _coloursCfg, activator);
	}
	
	
	@Override
	public int getResID_Icon_Leaderboard() {
		return R.drawable.btn_gps_games_badge_green;
	}
	
	
	@Override
	public int getResID_Button_OpenLeaderboard() {
		return R.drawable.ic_play_games_badge_leaderboards_green;
	}


	@Override
	public int getResID_Button_OpenAchievements() {
		return R.drawable.ic_play_games_badge_achievements_green;
	}
}
