package com.apps.mobile.android.commons.engagement.leaderboards;


import android.content.Context;
import android.graphics.RectF;

import com.apps.mobile.android.commons.R;
import com.apps.mobile.android.commons.cfg.colours.IConfigurationColours;
import com.apps.mobile.android.commons.engagement.IEngagementProvider;


public class View_Achievements_And_Leaderboards_Offline extends View_Achievements_And_Leaderboards_Base {
	
	
	public View_Achievements_And_Leaderboards_Offline(Context context, RectF _rectf_main, IEngagementProvider provider, IConfigurationColours _coloursCfg, IViewActivator activator) {
		super(context, _rectf_main, provider, _coloursCfg, activator);
	}
	
	
	@Override
	public int getResID_Icon_Leaderboard() {
		return R.drawable.ic_star_gold;
	}
	
	
	@Override
	public int getResID_Button_OpenLeaderboard() {
		return R.drawable.ic_123;
	}
	
	
	@Override
	public int getResID_Button_OpenAchievements() {
		return R.drawable.ic_cup;
	}
}
