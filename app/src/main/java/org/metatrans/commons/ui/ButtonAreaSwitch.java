package org.metatrans.commons.ui;


import android.graphics.RectF;


public class ButtonAreaSwitch extends ButtonAreaClick {


	//True when the represented by this button option is active / enabled
	private boolean active;

	private int colour_area_enabled;

	private int colour_area_disabled;


	public ButtonAreaSwitch(RectF _rect, String _text, int _colour_area_enabled, int _colour_area_disabled, int _colour_text, int _colour_selection, boolean _active) {

		super(_rect, _text, -1, _colour_text, _colour_selection);

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