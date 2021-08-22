package com.apps.mobile.android.commons.cfg.achievements;


public abstract class Config_Achievement_Base implements IConfigurationAchievements {
	
	
	@Override
	public int geIDReference() {
		return 0;
	}
	
	
	@Override
	public String getDescription_String() {
		return null;
	}
	
	
	@Override
	public String getName_String() {
		return null;
	}
	
	
	@Override
	public boolean isHidden() {
		return false;
	}
}
