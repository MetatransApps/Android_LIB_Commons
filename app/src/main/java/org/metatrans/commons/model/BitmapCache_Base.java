package org.metatrans.commons.model;


import android.graphics.Bitmap;

import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.ui.utils.BitmapUtils;
import org.metatrans.commons.ui.utils.ScreenUtils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


public class BitmapCache_Base implements I2DBitmapCache {


	public static BitmapCache_Base STATIC;


	public static final int MAX_BITMAP_SIZE = getMaxBitmapSize();


	private static final Map<Integer, BitmapCache_Base> all_cfgs 	= new HashMap<Integer, BitmapCache_Base>();


	private static final int getMaxBitmapSize() {

		int[] size_xy = ScreenUtils.getScreenSize(Application_Base.getInstance());

		return (int) (0.1759 * Math.max(size_xy[0], size_xy[1]));
	}


	public static I2DBitmapCache getStaticInstance(Integer tag) {

		synchronized (all_cfgs) {

			I2DBitmapCache cur = all_cfgs.get(tag);

			if (cur == null) {

				createInstance(tag);

				cur = all_cfgs.get(tag);
			}

			return cur;
		}
	}


	protected static void createInstance(Integer tag) {


		synchronized (all_cfgs) {

			BitmapCache_Base new_inst = all_cfgs.get(tag);

			if (new_inst != null) {

				throw new IllegalStateException("BitmapCache instance with tag '" + tag + "' already exists.");
			}

			if (new_inst == null) {

				new_inst = new BitmapCache_Base(tag);
			}

			all_cfgs.put(tag, new_inst);
		}
	}


	/**
     * Instance members
	 */
	private int id;

	private Hashtable<Integer, Bitmap> cache;


	protected BitmapCache_Base(Integer _id) {

		id = _id;

		cache = new Hashtable();
	}


	public void initBitmaps() {

		//Do nothing - the class could be extended and the method overridden
	}


	@Override
	public I2DBitmapCache getInstance(int ID) {

		return getStaticInstance(ID);
	}


	public BitmapCache_Base getInstance_Impl(int ID) {

		return (BitmapCache_Base) getInstance(ID);
	}


	public Bitmap get(Integer BITMAP_ID) {

		Bitmap result =  cache.get(BITMAP_ID);

		if (result == null) {

			throw new IllegalStateException("BITMAP_ID=" + BITMAP_ID + " not found.");
		}

		return result;
	}


	public Bitmap getRotated(Integer BITMAP_ID, int degrees) {

		Bitmap result =  cache.get(BITMAP_ID);

		return result;
	}


	public void add(Integer BITMAP_ID, Bitmap bitmap, boolean fit_to_max_size) {

		if (fit_to_max_size) {

			float scale_factor = MAX_BITMAP_SIZE / (float) Math.max(bitmap.getWidth(), bitmap.getHeight());

			bitmap = BitmapUtils.createScaledBitmap(bitmap,
					(int) (scale_factor * bitmap.getWidth()),
					(int) (scale_factor * bitmap.getHeight()));
		}

		cache.put(BITMAP_ID, bitmap);
	}


	public void add(Integer BITMAP_ID, Bitmap bitmap) {

		add(BITMAP_ID, bitmap, true);
	}
}
