package org.metatrans.commons.storage;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Map;

import org.metatrans.commons.app.Application_Base;

import android.app.Activity;
import android.content.Context;


public class StorageUtils {
	
	
	private static Map<String, Object> cache = new Hashtable<String, Object>();
	
	
	public static synchronized boolean storageFileExists(Context context, String storename) {
		File file = new File(context.getFilesDir(), storename);
		return file.exists();
	}
	
	
	public static synchronized Object readStorage(Context context, String storename) {
		
		Object cached = cache.get(storename);
		
		if (cached != null) {

			return cached;
		}
		
		ObjectInputStream input = null;
		
		Object result = null;
		
		try {
			
			File file = new File(context.getFilesDir(), storename);
			if (file.exists()) {
				InputStream is = context.openFileInput(storename);
				InputStream buffer = new BufferedInputStream(is);
				input = new ObjectInputStream(buffer);
				
				result = input.readObject();
			}
			
			if (result != null) {
				cache.put(storename, result);
			}
			
		} catch (Exception e) {
			//throw e;
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {}
			}
		}
		
		return result;
	}
	
	
	public static synchronized void writeStore(Context context, String storename) {
		
		Object cached = cache.get(storename);
		if (cached == null) {
			
			if (Application_Base.getInstance().isTestMode()) {
				throw new IllegalStateException("Cached object is null in static utils");
			}
			
			//No need to write. It is not used and hence not edited.
			return;
		}
		
		writeStore(context, storename, cached);
	}
	
	
	public static synchronized void writeStore(Context context, String storename, Object obj) {
		
		ObjectOutput output = null;
		
		try {
			
			OutputStream os = context.openFileOutput(storename, Activity.MODE_PRIVATE);
			OutputStream buffer = new BufferedOutputStream(os);
			output = new ObjectOutputStream(buffer);
			
			output.writeObject(obj);
			
			output.flush();
			
			cache.put(storename, obj);
			
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {}
			}
		}
	}
	

	public static synchronized void clearStore(Context context, String storename) {
		
		cache.remove(storename);
		
		writeStore(context, storename, null);
	}
}
