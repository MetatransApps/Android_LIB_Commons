package org.metatrans.commons.cfg.colours;


import android.graphics.Color;

import org.metatrans.commons.R;


public class Config_Colours_BlueSkyOnGray extends Config_Colours_Base {


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
		return Color.rgb((int) (0.75 * 153), (int) (0.75 * 153), (int) (0.75 * 153));
	}


	@Override
	public int getColour_Delimiter() {
		return Color.rgb(153, 153, 153);
	}
	
	
	public int getColour_Square_Black() {
		return Color.rgb((int) (0.75 * 135), (int) (0.75 * 206), (int) (0.75 * 235));
	}
	
	
	public int getColour_Square_White() {
		return Color.rgb(135, 206, 235);
	}
}
