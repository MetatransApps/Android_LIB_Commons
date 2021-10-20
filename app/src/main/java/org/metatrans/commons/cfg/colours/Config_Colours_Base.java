package org.metatrans.commons.cfg.colours;


import org.metatrans.commons.cfg.ConfigurationEntry_Base;

import android.graphics.Color;


public abstract class Config_Colours_Base extends ConfigurationEntry_Base implements IConfigurationColours {
	

	private static final float percent = 0.9f;


	public Config_Colours_Base() {
		
	}


	@Override
	public int getColour_Square_InvalidSelection() {
		return Color.rgb(255, (int) (percent * Color.green(getColour_Square_White())), (int) (percent * Color.blue(getColour_Square_White())));
	}


	@Override
	public int getColour_Square_ValidSelection() {
		return Color.rgb((int) (percent * Color.red(getColour_Square_White())), 255, (int) (percent * Color.blue(getColour_Square_White())));
	}


	@Override
	public int getColour_Square_MarkingSelection() {
		return Color.rgb((int) (percent * Color.red(getColour_Square_White())), (int) (percent * Color.green(getColour_Square_White())), 255);
	}	
}
