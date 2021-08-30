package org.metatransapps.commons.cfg.difficulty;


import org.metatransapps.commons.R;
import org.metatransapps.commons.cfg.ConfigurationEntry_Base;


public abstract class Config_Difficulty_13 extends ConfigurationEntry_Base implements IConfigurationDifficulty {

	
	@Override
	public int getID() {
		return MODE_COMPUTER_ENGINE_4MINS;
	}

	@Override
	public int getName() {
		return R.string.menu_difficulty_13;
	}

	@Override
	public int getIconResID() {
		return R.drawable.ic_difficulty_v4_l1;
	}
}
