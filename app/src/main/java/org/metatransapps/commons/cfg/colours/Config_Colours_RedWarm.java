package org.metatransapps.commons.cfg.colours;


import org.metatransapps.commons.R;

import android.graphics.Color;


public class Config_Colours_RedWarm extends Config_Colours_Base {
	
	
	public Config_Colours_RedWarm() {
		super();
	}
	
	
	@Override
	public int getID() {
		return CFG_COLOUR_RED_WARM;
	}
	
	
	@Override
	public int getName() {
		return R.string.colour_scheme_red_warm;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_colours_redwarm;
	}
	
	
	@Override
	public int getColour_Background() {
		return Color.rgb(119, 74, 57);
	}


	@Override
	public int getColour_Delimiter() {
		return Color.rgb(140, 101, 87);
	}
	
	
	public int getColour_Square_Black() {
		return Color.rgb(161, 129, 118);
	}
	
	public int getColour_Square_White() {
		return Color.rgb(181, 156, 147);
		//return Color.rgb(201, 183, 176);
	}
}
