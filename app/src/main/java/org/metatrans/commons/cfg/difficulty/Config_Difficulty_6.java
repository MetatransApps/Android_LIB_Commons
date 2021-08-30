package org.metatrans.commons.cfg.difficulty;


import org.metatrans.commons.R;
import org.metatrans.commons.cfg.ConfigurationEntry_Base;


public abstract class Config_Difficulty_6 extends ConfigurationEntry_Base implements IConfigurationDifficulty {

	
	@Override
	public int getID() {
		return MODE_COMPUTER_ENGINE_1SEC;
	}

	@Override
	public int getName() {
		return R.string.menu_difficulty_6;
	}

	@Override
	public int getIconResID() {
		return R.drawable.ic_difficulty_v2_l2;
	}
}
