package org.metatrans.commons.cfg.colours;


import android.graphics.Color;

import org.metatrans.commons.R;


public class Config_Colours_BlueSkyOnGray extends Config_Colours_Base {


	private static float factor = 0.5f;


	public Config_Colours_BlueSkyOnGray() {
		super();
	}
	
	
	@Override
	public int getID() {
		return CFG_COLOUR_BLUE_SKY_ON_GRAY;
	}
	
	
	@Override
	public int getName() {
		return R.string.colour_scheme_blue_sky_on_gray;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_colours_blueskyongray;
	}


	@Override
	public int getColour_Background() {
		return Color.rgb((int) (factor * 153), (int) (factor * 153), (int) (factor * 153));
	}


	@Override
	public int getColour_Delimiter() {
		return Color.rgb(153, 153, 153);
	}
	
	
	public int getColour_Square_Black() {
		return Color.rgb((int) (factor * 135), (int) (factor * 206), (int) (factor * 235));
	}
	
	
	public int getColour_Square_White() {
		return Color.rgb(135, 206, 235);
	}
}
