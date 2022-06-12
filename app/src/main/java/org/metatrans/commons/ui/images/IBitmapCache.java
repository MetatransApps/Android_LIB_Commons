package org.metatrans.commons.ui.images;


import android.content.Context;
import android.graphics.Bitmap;


public interface IBitmapCache {
	public Bitmap getBitmap(Context context, int imageID);
	public void remove(Integer id);
}
