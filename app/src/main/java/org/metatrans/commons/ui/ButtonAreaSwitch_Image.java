package org.metatrans.commons.ui;


import android.graphics.Bitmap;
import android.graphics.RectF;


public class ButtonAreaSwitch_Image extends ButtonAreaClick_Image {


	//True when the represented by this button option is active / enabled
	private boolean active;

	private int colour_area_enabled;

	private int colour_area_disabled;


	public ButtonAreaSwitch_Image(RectF _rect, Bitmap _bitmap, int _colour_area_enabled, int _colour_area_disabled, int _colour_selection, boolean _active) {
		this(_rect, _bitmap, _colour_area_enabled,_colour_area_disabled, _colour_selection, _active, true);
	}


	public ButtonAreaSwitch_Image(RectF _rect, Bitmap _bitmap, int _colour_area_enabled, int _colour_area_disabled, int _colour_selection, boolean _active, boolean scaleImage) {

		super(_rect, _bitmap, -1, _colour_selection, scaleImage);

		active = _active;

		colour_area_enabled = _colour_area_enabled;

		colour_area_disabled = _colour_area_disabled;

		if (active) {

			activate();

		} else {

			deactivate();
		}
	}


	public void activate() {

		active = true;

		if (isSelected()) {

			setColour_Area(colour_selection);

		} else {

			setColour_Area(colour_area_enabled);
		}
	}


	public void deactivate() {

		active = false;

		if (isSelected()) {

			setColour_Area(colour_selection);

		} else {

			setColour_Area(colour_area_disabled);
		}
	}


	/*@Override
	public void select() {

		super.select();

	}*/


	@Override
	public void deselect() {

		super.deselect();

		if (active) {

			setColour_Area(colour_area_enabled);

		} else {

			setColour_Area(colour_area_disabled);

		}
	}


	public boolean isActive() {
		return active;
	}
}