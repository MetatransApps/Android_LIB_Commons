package org.metatrans.commons.cfg.colours;


import org.metatrans.commons.R;

import android.graphics.Color;


public class Config_Colours_BlueDove extends Config_Colours_Base {
	
	
	public Config_Colours_BlueDove() {
		super();
	}
	
	
	@Override
	public int getID() {
		return CFG_COLOUR_BLUE_DOVE;
	}
	
	
	@Override
	public int getName() {
		return R.string.colour_scheme_blue_dove;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_colours_bluedove;
	}
	
	
	@Override
	public int getColour_Background() {
		return Color.rgb(68, 105, 125);
	}


	@Override
	public int getColour_Delimiter() {
		return Color.rgb(96, 127, 143);
	}
	
	
	public int getColour_Square_Black() {
		return Color.rgb(125, 150, 164);
	}
	
	public int getColour_Square_White() {
		return Color.rgb(152, 173, 183);
		//return Color.rgb(180, 195, 203);
	}
}
