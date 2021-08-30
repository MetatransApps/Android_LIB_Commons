package org.metatrans.commons.cfg.colours;


import org.metatrans.commons.R;

import android.graphics.Color;


public class Config_Colours_GreenCool extends Config_Colours_Base {
	
	
	public Config_Colours_GreenCool() {
		super();
	}
	
	
	@Override
	public int getID() {
		return CFG_COLOUR_GREEN_COOL;
	}
	
	
	@Override
	public int getName() {
		return R.string.colour_scheme_green_cool;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_colours_greencool;
	}
	
	
	@Override
	public int getColour_Background() {
		return Color.rgb(73, 108, 96);
	}


	@Override
	public int getColour_Delimiter() {
		return Color.rgb(101, 129, 120);
	}
	
	
	public int getColour_Square_Black() {
		return Color.rgb(129, 152, 144);
	}
	
	public int getColour_Square_White() {
		return Color.rgb(156, 174, 168);
		//return Color.rgb(183/196/191);
	}
}
