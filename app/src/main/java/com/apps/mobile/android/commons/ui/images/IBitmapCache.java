package com.apps.mobile.android.commons.ui.images;


import android.content.Context;
import android.graphics.Bitmap;


public interface IBitmapCache {
	public void addBitmap(int imageID, Bitmap bitmap);
	public Bitmap getBitmap(Context context, int imageID);
	public void remove(Integer id);
}
