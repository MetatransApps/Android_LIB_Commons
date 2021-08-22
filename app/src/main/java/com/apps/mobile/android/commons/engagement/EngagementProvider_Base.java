package com.apps.mobile.android.commons.engagement;


public class EngagementProvider_Base implements IEngagementProvider {
	
	
	private ISocialProvider socialProvider;
	private ILeaderboardsProvider leaderboardsProvider;
	private IAchievementsProvider achievementsProvider;
	
	
	@Override
	public ISocialProvider getSocialProvider() {
		return socialProvider;
	}
	
	
	@Override
	public ILeaderboardsProvider getLeaderboardsProvider() {
		return leaderboardsProvider;
	}
	
	
	@Override
	public IAchievementsProvider getAchievementsProvider() {
		return achievementsProvider;
	}


	@Override
	public void setSocialProvider(ISocialProvider socialProvider) {
		this.socialProvider = socialProvider;
	}


	@Override
	public void setLeaderboardsProvider(ILeaderboardsProvider leaderboardsProvider) {
		this.leaderboardsProvider = leaderboardsProvider;
	}


	@Override
	public void setAchievementsProvider(IAchievementsProvider achievementsProvider) {
		this.achievementsProvider = achievementsProvider;
	}
}
