package org.metatransapps.commons.cfg.colours;


import org.metatransapps.commons.R;

import android.graphics.Color;


public class Config_Colours_Gray extends Config_Colours_Base {
	
	
	public Config_Colours_Gray() {
		super();
	}
	
	
	@Override
	public int getID() {
		return CFG_COLOUR_GRAY;
	}
	
	
	@Override
	public int getName() {
		return R.string.colour_scheme_gray;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_colours_gray;
	}
	
	
	@Override
	public int getColour_Background() {
		return Color.rgb(5, 5, 5);
	}


	@Override
	public int getColour_Delimiter() {
		return Color.rgb(51, 51, 51);
	}
	
	
	public int getColour_Square_Black() {
		return Color.rgb(102, 102, 102);
	}
	
	public int getColour_Square_White() {
		return Color.rgb(153, 153, 153);
		//return Color.rgb(204, 204, 204);
	}
}
