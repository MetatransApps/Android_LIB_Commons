package com.apps.mobile.android.commons.engagement;


public interface IEngagementProvider {
	
	public ISocialProvider getSocialProvider();
	
	public ILeaderboardsProvider getLeaderboardsProvider();
	
	public IAchievementsProvider getAchievementsProvider();
	
	public void setSocialProvider(ISocialProvider socialProvider);

	public void setLeaderboardsProvider(ILeaderboardsProvider leaderboardsProvider);

	public void setAchievementsProvider(IAchievementsProvider achievementsProvider);
}
