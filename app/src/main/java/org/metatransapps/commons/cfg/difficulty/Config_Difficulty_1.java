package org.metatransapps.commons.cfg.difficulty;


import org.metatransapps.commons.R;
import org.metatransapps.commons.cfg.ConfigurationEntry_Base;


public abstract class Config_Difficulty_1 extends ConfigurationEntry_Base implements IConfigurationDifficulty {

	
	@Override
	public int getID() {
		return MODE_COMPUTER_RANDOM;
	}

	@Override
	public int getName() {
		return R.string.menu_difficulty_1;
	}

	@Override
	public int getIconResID() {
		return R.drawable.ic_difficulty_v1_l1;
	}
}
