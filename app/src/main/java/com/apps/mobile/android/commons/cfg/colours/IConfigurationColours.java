package com.apps.mobile.android.commons.cfg.colours;

import com.apps.mobile.android.commons.cfg.IConfigurationEntry;


public interface IConfigurationColours extends IConfigurationEntry {
	
	
	public static int CFG_COLOUR_BROWN 			= 1;
	public static int CFG_COLOUR_BLUE_DOVE 		= 2;
	public static int CFG_COLOUR_BLUE_PETROL 	= 3;
	public static int CFG_COLOUR_GREEN_WARM 	= 4;
	public static int CFG_COLOUR_GREEN_COOL 	= 5;
	public static int CFG_COLOUR_RED_WARM 		= 6;
	public static int CFG_COLOUR_RED_COOL 		= 7;
	public static int CFG_COLOUR_VIOLET_MAUVE 	= 8;
	public static int CFG_COLOUR_GRAY 			= 9;
	public static int CFG_COLOUR_BLUE_STRONG 	= 10;
	
	public int getColour_Background();
	public int getColour_Delimiter();
	public int getColour_Square_Black();
	public int getColour_Square_White();
	
	public int getColour_Square_ValidSelection();
	public int getColour_Square_InvalidSelection();
	public int getColour_Square_MarkingSelection();
}
