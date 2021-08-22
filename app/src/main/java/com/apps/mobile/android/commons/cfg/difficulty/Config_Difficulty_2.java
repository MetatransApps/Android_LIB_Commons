package com.apps.mobile.android.commons.cfg.difficulty;


import com.apps.mobile.android.commons.R;
import com.apps.mobile.android.commons.cfg.ConfigurationEntry_Base;


public abstract class Config_Difficulty_2 extends ConfigurationEntry_Base implements IConfigurationDifficulty {

	
	@Override
	public int getID() {
		return MODE_COMPUTER_RANDOM_BUT_CAPTURE;
	}

	@Override
	public int getName() {
		return R.string.menu_difficulty_2;
	}

	@Override
	public int getIconResID() {
		return R.drawable.ic_difficulty_v1_l2;
	}
}
