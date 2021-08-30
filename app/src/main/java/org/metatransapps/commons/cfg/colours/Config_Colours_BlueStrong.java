package org.metatransapps.commons.cfg.colours;


import org.metatransapps.commons.R;

import android.graphics.Color;


public class Config_Colours_BlueStrong extends Config_Colours_Base {
	
	
	public Config_Colours_BlueStrong() {
		super();
	}
	
	
	@Override
	public int getID() {
		return CFG_COLOUR_BLUE_STRONG;
	}
	
	
	@Override
	public int getName() {
		return R.string.colour_scheme_blue_petrol;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_colours_bluepetrol;
	}
	
	
	@Override
	public int getColour_Background() {
		return Color.rgb(0, 153, 204);
	}


	@Override
	public int getColour_Delimiter() {
		return Color.rgb(51, 181, 229);
	}
	
	
	public int getColour_Square_Black() {
		return Color.rgb(109, 202, 236);
	}
	
	
	public int getColour_Square_White() {
		return Color.rgb(197, 234, 248);
	}
}
