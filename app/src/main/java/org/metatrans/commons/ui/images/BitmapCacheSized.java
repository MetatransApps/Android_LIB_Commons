package org.metatrans.commons.ui.images;



import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.ui.utils.BitmapUtils;

import android.content.Context;
import android.graphics.Bitmap;


public class BitmapCacheSized extends BitmapCacheBase implements IBitmapCache {

	
	private IBitmapCache parent;
	private int sizeOfBitmaps;
	
	
	public BitmapCacheSized(int _sizeOfBitmaps, IBitmapCache _parent, int cacheSize) {
		super(cacheSize);
		sizeOfBitmaps = _sizeOfBitmaps;
		parent = _parent;
	}
	
	
	public int getBitmapsSize() {
		return sizeOfBitmaps;
	}
	
	
	@Override
	public synchronized void remove(Integer imageID) {
		
		parent.remove(imageID);
		
		super.remove(imageID);
	}
	
	
	public void addBitmap(int imageID, Bitmap bitmap) {
		
		if (Application_Base.getInstance().isTestMode()) {
			if (getBitmapSize(bitmap) != sizeOfBitmaps) {
				throw new IllegalStateException("getBitmapSize(bitmap)=" + getBitmapSize(bitmap) + ", size=" + sizeOfBitmaps);
			}
		}
		
		super.addBitmap(imageID, bitmap);
	}
	
	
	public Bitmap getBitmap(Context context, int imageID) {
		Bitmap bitmap = bitmaps.get(imageID);
		if (bitmap == null) {
			bitmap = parent.getBitmap(context, imageID);
			if (bitmap != null) {
				//float factor = getBitmapSize(bitmap) / size;
				bitmap = BitmapUtils.createScaledBitmap(bitmap, sizeOfBitmaps, sizeOfBitmaps, false);
				if (getBitmapSize(bitmap) < sizeOfBitmaps) {
					System.out.println("Image with ID " + imageID + " has not enough quality for size " + sizeOfBitmaps + ".");
				}
				addBitmap(imageID, bitmap);
			}
		}
		return bitmap;
	}
}
