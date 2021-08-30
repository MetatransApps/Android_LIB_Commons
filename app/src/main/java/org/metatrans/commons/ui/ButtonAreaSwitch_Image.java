package org.metatrans.commons.ui;


import android.graphics.Bitmap;
import android.graphics.RectF;


public class ButtonAreaSwitch_Image extends ButtonAreaClick_Image {
	
	
	public ButtonAreaSwitch_Image(RectF _rect, Bitmap _bitmap, int _colour_area, int _colour_selection, boolean _active) {
		super(_rect, _bitmap, _colour_area, _colour_selection, true);
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
		
		boolean backup = active;
		if (backup) {
			super.select();
		} else {
			super.deselect();
		}
		active = backup;
	}
}