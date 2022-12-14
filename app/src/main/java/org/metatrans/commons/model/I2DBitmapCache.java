package org.metatrans.commons.model;


import android.graphics.Bitmap;


public interface I2DBitmapCache {


    public static final int BITMAP_ID_STATIC 					= 0;

    public static final int BITMAP_ID_COMMON 					= 1;


    public I2DBitmapCache getInstance(int ID);

    public Bitmap get(Integer BITMAP_ID);

    public Bitmap getRotated(Integer BITMAP_ID, int degrees);
}
