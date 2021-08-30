package org.metatransapps.commons.cfg.colours;


import org.metatransapps.commons.cfg.ConfigurationEntry_Base;

import android.graphics.Color;


public abstract class Config_Colours_Base extends ConfigurationEntry_Base implements IConfigurationColours {
	
	
	private int const1 = 0; //diff
	private int const2 = 96; //sum
	
	
	public Config_Colours_Base() {
		
	}
	
	
	private int getMidRed() {
		return (Color.red(getColour_Square_White()) + Color.red(getColour_Square_Black())) / 2;
	}
	
	private int getMidGreen() {
		return (Color.green(getColour_Square_White()) + Color.green(getColour_Square_Black())) / 2;
	}
	
	private int getMidBlue() {
		return (Color.blue(getColour_Square_White()) + Color.blue(getColour_Square_Black())) / 2;
	}
	
	@Override
	public int getColour_Square_ValidSelection() {
		return Color.rgb(Math.max(0, getMidRed() - const1), Math.min(255, getMidGreen() + const2), Math.max(0, getMidBlue() - const1));
	}


	@Override
	public int getColour_Square_InvalidSelection() {
		return Color.rgb(Math.min(255, getMidRed() + const2), Math.max(0, getMidGreen() - const1), Math.max(0, getMidBlue() - const1));
	}
	
	
	@Override
	public int getColour_Square_MarkingSelection() {
		return Color.rgb(Math.max(0, getMidRed() - const1), Math.max(0, getMidGreen() - const1), Math.min(255, getMidBlue() + const2));
	}	
}
