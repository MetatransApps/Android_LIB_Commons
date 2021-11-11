package org.metatrans.commons.ui;


import android.graphics.RectF;


public class ButtonAreaClick extends TextArea {


	//True when user finger is moving inside the button's rectangle
	protected boolean selected = false;

	protected int colour_selection;
	
	
	public ButtonAreaClick(RectF _rect, String _text, int _colour_area, int _colour_text, int _colour_selection) {

		super(_rect, _text, _colour_area, _colour_text);

		colour_selection = _colour_selection;
	}


	public ButtonAreaClick(RectF _rect, String _text, boolean drawTextOnly, int _colour_area, int _colour_text, int _colour_selection) {

		super(_rect, drawTextOnly, _text, _colour_area, _colour_text);

		colour_selection = _colour_selection;
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