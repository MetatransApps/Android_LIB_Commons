package org.metatrans.commons.cfg.colours;


import android.graphics.Color;

import org.metatrans.commons.R;


public class Config_Colours_AutoByBaseColor extends Config_Colours_Base {


	private int id;

	private int color1;
	private int color2;
	private int color3;
	private int color4;


	public Config_Colours_AutoByBaseColor(int id, int r, int g, int b) {

		super();

		this.id = id;

		int min = Math.min(r, Math.min(g, b));

		if (min < 128) {
			throw new IllegalStateException("min=" + min);
		}

		int step = (min - (255 - min)) / 3;

		color1 = Color.rgb(r - 0 * step, g - 0 * step, b - 0 * step);
		color2 = Color.rgb(r - 1 * step, g - 1 * step, b - 1 * step);
		color3 = Color.rgb(r - 2 * step, g - 2 * step, b - 2 * step);
		color4 = Color.rgb(r - 3 * step, g - 3 * step, b - 3 * step);

	}
	
	
	@Override
	public int getID() {
		return id;
	}
	
	
	@Override
	public int getName() {
		return R.string.colour_scheme_blue_petrol;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_colours_bluelightly;
	}
	
	
	@Override
	public int getColour_Background() {
		return color4;
	}


	@Override
	public int getColour_Delimiter() {
		return color3;
	}


	@Override
	public int getColour_Square_Black() {
		return color2;
	}


	@Override
	public int getColour_Square_White() {
		return color1;
	}
}
