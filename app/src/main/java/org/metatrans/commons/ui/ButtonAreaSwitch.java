package org.metatrans.commons.ui;


import android.graphics.RectF;


public class ButtonAreaSwitch extends ButtonAreaClick {
	
	
	public ButtonAreaSwitch(RectF _rect, String _text, int _colour_area, int _colour_text, int _colour_selection, boolean _active) {
		super(_rect, _text, _colour_area, _colour_text, _colour_selection);
		active = _active;
		if (active){
			super.select();
		}
	}
	
	
	@Override
	public void select() {
		boolean backup = active;
		if (backup) {
			super.deselect();
		} else {
			super.select();
		}
		active = backup;
	}
	
	
	@Override
	public void deselect() {
		boolean backup = active;
		if (backup) {
			super.select();
		} else {
			super.deselect();
		}
		active = backup;
	}
	
	
	public void finish() {

		active = !active;

		if (active) {

			super.select();

		} else {

			super.deselect();
		}
	}
}