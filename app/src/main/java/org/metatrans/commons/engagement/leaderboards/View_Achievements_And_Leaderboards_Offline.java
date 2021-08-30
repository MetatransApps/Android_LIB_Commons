package org.metatrans.commons.engagement.leaderboards;


import org.metatrans.commons.R;
import org.metatrans.commons.cfg.colours.IConfigurationColours;
import org.metatrans.commons.engagement.IEngagementProvider;

import android.content.Context;
import android.graphics.RectF;


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
