package org.metatransapps.commons.cfg.colours;


import org.metatransapps.commons.R;

import android.graphics.Color;


public class Config_Colours_Brown extends Config_Colours_Base {
	
	
	public Config_Colours_Brown() {
		super();
	}
	
	
	@Override
	public int getID() {
		return CFG_COLOUR_BROWN;
	}
	
	
	@Override
	public int getName() {
		return R.string.colour_scheme_brown;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_colours_brown;
	}
	
	
	@Override
	public int getColour_Background() {
		return Color.rgb(129, 110, 44);
	}


	@Override
	public int getColour_Delimiter() {
		return Color.rgb(148, 132, 75);
	}
	
	
	public int getColour_Square_Black() {
		return Color.rgb(167, 154, 108);
	}
	
	public int getColour_Square_White() {
		return Color.rgb(186, 176, 139);
		//return Color.rgb(205, 197, 171);
	}
}
