package com.apps.mobile.android.commons.cfg.colours;


import com.apps.mobile.android.commons.R;

import android.graphics.Color;


public class Config_Colours_BluePetrol extends Config_Colours_Base {
	
	
	public Config_Colours_BluePetrol() {
		super();
	}
	
	
	@Override
	public int getID() {
		return CFG_COLOUR_BLUE_PETROL;
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
		return Color.rgb(21, 101, 112);
	}


	@Override
	public int getColour_Delimiter() {
		return Color.rgb(98, 146, 147);
	}
	
	
	public int getColour_Square_Black() {
		return Color.rgb(127, 166, 167);
	}
	
	public int getColour_Square_White() {
		return Color.rgb(154, 185, 185);
		//return Color.rgb(181, 204, 204);
	}
}
