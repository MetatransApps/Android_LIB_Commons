package org.metatrans.commons.ui.images;



import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.ui.utils.BitmapUtils;

import android.content.Context;
import android.graphics.Bitmap;


public class BitmapCacheSized extends BitmapCacheBase implements IBitmapCache {


	private int sizeOfBitmaps;
	
	
	public BitmapCacheSized(int _sizeOfBitmaps, int cacheSize) {

		super(cacheSize);
		
		sizeOfBitmaps = _sizeOfBitmaps;
	}
	
	
	public int getBitmapsSize() {
		return sizeOfBitmaps;
	}
	
	
	@Override
	public synchronized void remove(Integer imageID) {
		
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


	/**
	 * This method calls addBitmap(imageID, bitmap); after scaling the image
	 */
	@Override
	public synchronized Bitmap getBitmap(Context context, int imageID, float height_scale, float width_scale) {

		Bitmap bitmap = bitmaps.get(imageID);

		if (bitmap == null) {

			System.out.println("Bitmap with ID " + imageID + " not found. Cache instance=" + this);

			bitmap = BitmapUtils.fromResource(context, imageID);

			bitmap = BitmapUtils.cropTransparantPart(bitmap);

			final int BOTTOM_MARGIN = Math.max(1, (int) (0.11f * bitmap.getHeight()));

			bitmap = BitmapUtils.generateTransparantPart(bitmap, height_scale, width_scale, BOTTOM_MARGIN);

			bitmap = BitmapUtils.createScaledBitmap(bitmap, sizeOfBitmaps, sizeOfBitmaps);

			if (getBitmapSize(bitmap) < sizeOfBitmaps) {
				
				System.out.println("Image with ID " + imageID + " has not enough quality for size " + sizeOfBitmaps + ".");
			}

			addBitmap(imageID, bitmap);
		}

		return bitmap;
	}
}
