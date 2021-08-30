package org.metatrans.commons.ui;


import android.graphics.RectF;


public class ButtonAreaClick extends TextArea {

	
	private int colour_selection;
	protected boolean active = false;
	
	
	public ButtonAreaClick(RectF _rect, String _text, int _colour_area, int _colour_text, int _colour_selection) {
		super(_rect, _text, _colour_area, _colour_text);
		colour_selection = _colour_selection;
	}
	
	
	public void select() {
		setColour_Area(colour_selection);
		active = true;
	}
	
	
	public void deselect() {
		setColour_Area(colour_area);
		active = false;
	}
	
	public boolean isActive() {
		return active;
	}
}