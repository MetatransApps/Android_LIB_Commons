package org.metatransapps.commons.cfg.difficulty;


import org.metatransapps.commons.R;
import org.metatransapps.commons.cfg.ConfigurationEntry_Base;


public abstract class Config_Difficulty_3 extends ConfigurationEntry_Base implements IConfigurationDifficulty {

	
	@Override
	public int getID() {
		return MODE_COMPUTER_RANDOM_BUT_CAPTURE_AND_DEFENSE;
	}

	@Override
	public int getName() {
		return R.string.menu_difficulty_3;
	}

	@Override
	public int getIconResID() {
		return R.drawable.ic_difficulty_v1_l3;
	}
}
