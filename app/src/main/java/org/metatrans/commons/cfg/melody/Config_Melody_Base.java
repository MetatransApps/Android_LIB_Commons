package org.metatrans.commons.cfg.melody;


import org.metatrans.commons.R;
import org.metatrans.commons.cfg.ConfigurationEntry_Base;


public class Config_Melody_Base extends ConfigurationEntry_Base implements IConfigurationMelody {


	private int id;

	private int name_res_id;

	public Config_Melody_Base(int _id, int _name_res_id) {

		id = _id;

		name_res_id = _name_res_id;
	}


	/*@Override
	public boolean isEnabled() {

		return id != CFG_MELODY_NONE;
	}*/


	@Override
	public int getID() {

		return id;
	}


	@Override
	public int getName() {

		return name_res_id;
	}


	@Override
	public int getIconResID() {

		return R.drawable.ic_action_headphones;
	}
}
