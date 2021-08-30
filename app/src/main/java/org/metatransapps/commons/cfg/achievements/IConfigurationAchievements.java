package org.metatransapps.commons.cfg.achievements;


import org.metatransapps.commons.cfg.IConfigurationEntry;


public interface IConfigurationAchievements extends IConfigurationEntry {
	
	public static int CFG_ACHIEVEMENT_INVITE_3_FRIENDS		= 90;
	public static int CFG_ACHIEVEMENT_STOP_PIECES			= 100;
	//public static int CFG_ACHIEVEMENT_READ_HELP				= 200;
	public static int CFG_ACHIEVEMENT_CHANGE_COLOURS 		= 300;
	//public static int CFG_ACHIEVEMENT_CHANGE_PIECES 		= 400;
	public static int CFG_ACHIEVEMENT_CHANGE_MODE	 		= 500;
	//public static int CFG_ACHIEVEMENT_CHANGE_DIFFICULTY 	= 600;

	
	public int getDescription();
	public int geIDReference();
	public String getName_String();
	public String getDescription_String();
	
	public int getScores();
	public int getMaxCount();
	public int getIncrementsCount();
	public boolean isHidden();
}
