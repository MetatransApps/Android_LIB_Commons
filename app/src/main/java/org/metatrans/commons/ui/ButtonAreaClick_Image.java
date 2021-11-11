package org.metatrans.commons.ui;


import android.graphics.Bitmap;
import android.graphics.RectF;


public class ButtonAreaClick_Image extends ImageArea {


	//True when user finger is moving inside the button's rectangle
	protected boolean selected = false;

	protected int colour_selection;
	
	
	public ButtonAreaClick_Image(RectF _rect, Bitmap _bitmap, int _colour_area, int _colour_selection, boolean scaleImage) {

		super(_rect, _bitmap, _colour_area, scaleImage);

		colour_selection = _colour_selection;
	}
	
	public ButtonAreaClick_Image(RectF _rect, Bitmap _bitmap, int _colour_area, int _colour_selection) {
		this(_rect, _bitmap, _colour_area, _colour_selection, true);
	}
	
	
	public void select() {

		selected = true;

		setColour_Area(colour_selection);
	}
	
	
	public void deselect() {

		selected = false;

		setColour_Area(colour_area);
	}


	public boolean isSelected() {

		return selected;
	}
}