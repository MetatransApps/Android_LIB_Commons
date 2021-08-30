package org.metatransapps.commons.achievements;


import org.metatransapps.commons.cfg.achievements.IConfigurationAchievements;

import android.content.Context;


public interface IAchievementsManager {
	
	public IConfigurationAchievements[] getAll();
	public IConfigurationAchievements getConfigByID(int cfgID);
	public IConfigurationAchievements getConfigByRefID(String cfgID);
	
	public void inc(Context context, int achievementID);
	public void inc(Context context, int achievementID, int increment);
	public void inc(Context context, int achievementID, int increment, boolean call_engagementProvider);
	
	public int get(Context context, int achievementID);
	
	public String countEarned(Context context);
	
	public void checkNotifications(Context context);
	
	public int getScores(Context activity);
	public int getMaxScores(Context activity);
	
	public boolean isEarned(Context context, int achievementID);
	public boolean isHidden(Context context, int achievementID);
}
