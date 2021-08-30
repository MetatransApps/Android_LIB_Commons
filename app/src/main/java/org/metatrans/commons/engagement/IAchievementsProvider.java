package org.metatrans.commons.engagement;


import org.metatrans.commons.cfg.colours.IConfigurationColours;

import android.graphics.RectF;
import android.view.View;


public interface IAchievementsProvider {

	public void unlock(int achievementID);
	
	public void increment(int achievementID, int increment);
	
	public void openAchievements();
	
	public View getAchievementsView(IConfigurationColours coloursCfg, RectF rectf);
	
	public boolean supportAchievementsCounting();
}
