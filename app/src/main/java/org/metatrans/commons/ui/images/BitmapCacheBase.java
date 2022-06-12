package org.metatrans.commons.ui.images;


import org.metatrans.commons.ui.utils.BitmapUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;


public class BitmapCacheBase implements IBitmapCache {
	
	
	@SuppressLint("UseSparseArrays")
	protected LruCache<Integer, Bitmap> bitmaps;
	
	
	public BitmapCacheBase(int size) {
		bitmaps = new LruCache<Integer, Bitmap>(size);
	}
	
	
	protected synchronized void addBitmap(int imageID, Bitmap bitmap) {

		System.out.println("BitmapCacheBase: Adding bitmap with ID " + imageID);

		if (bitmaps.get(imageID) != null) {

			throw new IllegalStateException("Image with ID " + imageID + " already exists");
			//System.out.println("BitmapCacheBase: Image with ID " + imageID + " already exists");
		}
		
		bitmaps.put(imageID, bitmap);
	}


	public synchronized Bitmap getBitmap(Context context, int imageID) {

		return getBitmap(context, imageID, 1, 1);
	}


	public synchronized Bitmap getBitmap(Context context, int imageID, float height_scale, float width_scale) {

		Bitmap bitmap = bitmaps.get(imageID);

		if (bitmap == null) {

			System.out.println("Bitmap with ID " + imageID + " not found. Cache instance=" + this);

			bitmap = BitmapUtils.fromResource(context, imageID);

			bitmap = BitmapUtils.cropTransparantPart(bitmap);

			final int BOTTOM_MARGIN = Math.max(1, (int) (0.11f * bitmap.getHeight()));

			bitmap = BitmapUtils.generateTransparantPart(bitmap, height_scale, width_scale, BOTTOM_MARGIN);

			addBitmap(imageID, bitmap);
		}

		return bitmap;
	}
	
	
	protected int getBitmapSize(Bitmap bitmap) {

		return bitmap.getHeight();
	}
	
	
	public synchronized void clear() {
		
		bitmaps.evictAll();
	}
	
	
	public synchronized int size() {

		return bitmaps.size();
	}
	
	
	@Override
	public synchronized void remove(Integer imgageID) {
		
		Bitmap bitmap = bitmaps.remove(imgageID);
	}
}
