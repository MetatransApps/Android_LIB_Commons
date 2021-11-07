package org.metatrans.commons.ui;


import android.graphics.Bitmap;
import android.graphics.RectF;


public class ButtonAreaClick_Image extends ImageArea {

	
	private int colour_selection;
	protected boolean active = false;
	
	
	public ButtonAreaClick_Image(RectF _rect, Bitmap _bitmap, int _colour_area, int _colour_selection, boolean scaleImage) {
		super(_rect, _bitmap, _colour_area, scaleImage);
		colour_selection = _colour_selection;
	}
	
	public ButtonAreaClick_Image(RectF _rect, Bitmap _bitmap, int _colour_area, int _colour_selection) {
		this(_rect, _bitmap, _colour_area, _colour_selection, true);
	}
	
	
	public void select() {
		setColour_Area(colour_selection);
		active = true;
	}
	
	
	public void deselect() {
		setColour_Area(colour_area);
		active = false;
	}


	public boolean isSelected() {

		return active;
	}
}