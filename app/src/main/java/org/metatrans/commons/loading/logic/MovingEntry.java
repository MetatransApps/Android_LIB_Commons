package org.metatrans.commons.loading.logic;


import java.util.List;

import android.graphics.Bitmap;


public class MovingEntry {


	//= 10;//(float) ((Math.random() - 0.5) * (STEP + 4 * entry.clicks * STEP));
	public float speed_x = (float) (50 * (Math.random() - 0.5f));
	public float speed_y = (float) (50 * (Math.random() - 0.5f));

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
