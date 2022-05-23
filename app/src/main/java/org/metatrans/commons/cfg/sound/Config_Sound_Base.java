package org.metatrans.commons.cfg.sound;


import org.metatrans.commons.R;
import org.metatrans.commons.cfg.ConfigurationEntry_Base;


public class Config_Sound_Base extends ConfigurationEntry_Base implements IConfigurationSound {


	private boolean enabled;


	public Config_Sound_Base(boolean _enabled) {

		enabled = _enabled;
	}


	@Override
	public boolean isEnabled() {

		return enabled;
	}


	@Override
	public int getID() {

		return enabled ? CFG_SOUND_ON : CFG_SOUND_OFF;
	}


	@Override
	public int getName() {

		return enabled ? R.string.on : R.string.silent;
	}


	@Override
	public int getIconResID() {

		return enabled ? R.drawable.ic_action_volume_up_white : R.drawable.ic_action_volume_mute_white;
	}
}
