package com.apps.mobile.android.commons.cfg.colours;


import com.apps.mobile.android.commons.R;

import android.graphics.Color;


public class Config_Colours_RedCool extends Config_Colours_Base {
	
	
	public Config_Colours_RedCool() {
		super();
	}
	
	
	@Override
	public int getID() {
		return CFG_COLOUR_RED_COOL;
	}
	
	
	@Override
	public int getName() {
		return R.string.colour_scheme_red_cool;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_colours_redcool;
	}
	
	
	@Override
	public int getColour_Background() {
		return Color.rgb(132, 76, 84);
	}


	@Override
	public int getColour_Delimiter() {
		return Color.rgb(150, 103, 110);
	}
	
	
	public int getColour_Square_Black() {
		return Color.rgb(169, 130, 136);
	}
	
	public int getColour_Square_White() {
		return Color.rgb(188, 157, 162);
		//return Color.rgb(206, 183, 187);
	}
}
