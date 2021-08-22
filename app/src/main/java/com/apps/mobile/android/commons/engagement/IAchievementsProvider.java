package com.apps.mobile.android.commons.engagement;


import android.graphics.RectF;
import android.view.View;

import com.apps.mobile.android.commons.cfg.colours.IConfigurationColours;


public interface IAchievementsProvider {

	public void unlock(int achievementID);
	
	public void increment(int achievementID, int increment);
	
	public void openAchievements();
	
	public View getAchievementsView(IConfigurationColours coloursCfg, RectF rectf);
	
	public boolean supportAchievementsCounting();
}
