package org.metatransapps.commons.ui.images;


import org.metatransapps.commons.ui.utils.BitmapUtils;

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
	
	
	public synchronized void addBitmap(int imageID, Bitmap bitmap) {
		
		if (bitmaps.get(imageID) != null) {
			throw new IllegalStateException("Image with ID " + imageID + " already exists");
		}
		
		//if (log) System.out.println("Added bitmap with ID " + imageID);
		
		bitmaps.put(imageID, bitmap);
	}
	
	
	public synchronized Bitmap getBitmap(Context context, int imageID) {
		
		Bitmap bitmap = bitmaps.get(imageID);
		if (bitmap == null) {
			
			//if (log)
			System.out.println("Bitmap with ID " + imageID + " not found. Cache instance=" + this);
			
			bitmap = BitmapUtils.fromResource(context, imageID);
			if (bitmap != null) {
				addBitmap(imageID, bitmap);
			}
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
