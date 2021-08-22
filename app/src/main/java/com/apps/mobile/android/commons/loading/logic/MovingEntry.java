package com.apps.mobile.android.commons.loading.logic;


import java.util.List;

import android.graphics.Bitmap;


public class MovingEntry {
	
	
	public Coordinates coordinates = new Coordinates();
	public List<Bitmap> bitmaps;
	public int clicks;
	
	
	public MovingEntry(float x, float y, List<Bitmap> _bitmaps) {
		coordinates.x = x;
		coordinates.y = y;
		bitmaps = _bitmaps;
	}
	
	
	public Bitmap getBitmap(int id) {
		if (id >= bitmaps.size()) {
			id = bitmaps.size() - 1;
		}
		return bitmaps.get(id);
	}
}
