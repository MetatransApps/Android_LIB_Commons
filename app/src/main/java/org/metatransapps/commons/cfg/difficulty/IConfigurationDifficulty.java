package org.metatransapps.commons.cfg.difficulty;


import org.metatransapps.commons.cfg.IConfigurationEntry;


public interface IConfigurationDifficulty extends IConfigurationEntry {
	
	public static final int MODE_COMPUTER_RANDOM							= 1;
	public static final int MODE_COMPUTER_RANDOM_BUT_CAPTURE				= 2;
	public static final int MODE_COMPUTER_RANDOM_BUT_CAPTURE_AND_DEFENSE 	= 3;
	public static final int MODE_COMPUTER_POSITIONAL_EVALUATION				= 4;
	public static final int MODE_COMPUTER_ENGINE_1PLY						= 5;
	public static final int MODE_COMPUTER_ENGINE_1SEC						= 6;
	public static final int MODE_COMPUTER_ENGINE_3SEC						= 7;
	public static final int MODE_COMPUTER_ENGINE_7SEC						= 8;
	public static final int MODE_COMPUTER_ENGINE_15SEC						= 9;
	public static final int MODE_COMPUTER_ENGINE_30SEC						= 10;
	public static final int MODE_COMPUTER_ENGINE_1MIN						= 11;
	public static final int MODE_COMPUTER_ENGINE_2MINS						= 12;
	public static final int MODE_COMPUTER_ENGINE_4MINS						= 13;
	public static final int MODE_COMPUTER_ENGINE_10MINS						= 14;
	public static final int MODE_COMPUTER_ENGINE_30MINS						= 15;
	public static final int MODE_COMPUTER_ENGINE_1HOUR						= 16;
	public static final int MODE_COMPUTER_MAXID								= MODE_COMPUTER_ENGINE_1HOUR;
}
