package org.metatrans.commons.cfg.melody;


import org.metatrans.commons.cfg.IConfigurationEntry;


public interface IConfigurationMelody extends IConfigurationEntry {


	public static int CFG_MELODY_NONE 			= 0;
	public static int CFG_MELODY_ADVENTURE		= 1;

	public static int CFG_MELODY_BATTLE			= 2;

	public boolean isEnabled();
}