package org.metatrans.commons.cfg.sound;

import org.metatrans.commons.cfg.IConfigurationEntry;


public interface IConfigurationSound extends IConfigurationEntry {


	public static int CFG_SOUND_OFF 		= 0;
	public static int CFG_SOUND_ON 			= 1;

	public boolean isEnabled();
}
