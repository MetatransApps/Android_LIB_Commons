package org.metatrans.commons.cfg.colours;


import org.metatrans.commons.R;

import android.graphics.Color;


public class Config_Colours_GreenWarm extends Config_Colours_Base {
	
	
	public Config_Colours_GreenWarm() {
		super();
	}
	
	
	@Override
	public int getID() {
		return CFG_COLOUR_GREEN_WARM;
	}
	
	
	@Override
	public int getName() {
		return R.string.colour_scheme_green_warm;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_colours_greenwarm;
	}
	
	
	@Override
	public int getColour_Background() {
		return Color.rgb(85, 118, 48);
	}


	@Override
	public int getColour_Delimiter() {
		return Color.rgb(110, 138, 79);
	}
	
	
	public int getColour_Square_Black() {
		return Color.rgb(136, 160, 111);
	}
	
	public int getColour_Square_White() {
		return Color.rgb(162, 180, 141);
		//return Color.rgb(187, 200, 172);
	}
}
