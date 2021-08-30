package org.metatransapps.commons.cfg.colours;


import org.metatransapps.commons.R;

import android.graphics.Color;


public class Config_Colours_VioletMauve extends Config_Colours_Base {
	
	
	public Config_Colours_VioletMauve() {
		super();
	}
	
	
	@Override
	public int getID() {
		return CFG_COLOUR_VIOLET_MAUVE;
	}
	
	
	@Override
	public int getName() {
		return R.string.colour_scheme_violet_mauve;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_colours_violetmauve;
	}
	
	
	@Override
	public int getColour_Background() {
		return Color.rgb(100, 68, 89);
	}


	@Override
	public int getColour_Delimiter() {
		return Color.rgb(123, 96, 114);
	}
	
	
	public int getColour_Square_Black() {
		return Color.rgb(147, 125, 139);
	}
	
	public int getColour_Square_White() {
		return Color.rgb(170, 152, 164);
		//return Color.rgb(193, 180, 189);
	}
}
