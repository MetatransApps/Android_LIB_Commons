package org.metatrans.commons.ui.utils;


import java.io.IOException;
import java.io.InputStream;

import org.metatrans.commons.app.Application_Base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;


public class BitmapUtils {
	
	
	private static Bitmap.Config bitmapConfig = Bitmap.Config.ARGB_8888;
	
	
	public static Bitmap fromResource(Context context, int resid) {
		
		Bitmap bitmap = null;
		
		InputStream is = null;
		try {

			is = context.getResources().openRawResource(resid);

			bitmap = BitmapFactory.decodeStream(is);

		} catch (Exception e) {

			//Do nothing
			e.printStackTrace();

		} finally {

			if (is != null) {

				try {

					is.close();

				} catch (IOException e) {
					//Do nothing
				}
			}
		}
		
		return bitmap;
	}
	
	
	public static void recycle(Bitmap b_new, Bitmap b_old) {
		//if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.GINGERBREAD) {
			if (b_new != b_old) { 
				b_old.recycle();
			}
		//}
	}
	
	
	public static Bitmap createScaledBitmap(Bitmap src, int dstWidth, int dstHeight, boolean filter) {
		
		//Fix for rare cases, where dstWidth and/or dstHeight is 0 or less. In this cases the Bitmap API throws IllegalArgumentException.
		if (!Application_Base.getInstance().isTestMode()) {
			if (dstWidth <= 0) {
				dstWidth = 1;
			}
			if (dstHeight <= 0) {
				dstHeight = 1;
			}
		}
		
		Bitmap result = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, filter);
		
		if (result == src) {
			
			result = Bitmap.createBitmap(dstWidth, dstHeight, bitmapConfig);
			
			Canvas canvas = new Canvas(result);
			
			Rect rect_src = new Rect(0, 0, src.getWidth(), src.getHeight());
			RectF rect_dest = new RectF();
			rect_dest.left = 0;
			rect_dest.top = 0;
			rect_dest.right = dstWidth;
			rect_dest.bottom = dstHeight;
			canvas.drawBitmap(src, rect_src, rect_dest, null);
		}
		
		return result;
	}
	
	
	public static Bitmap fromResource(Context context, int resid, int size) {
		
		Bitmap bitmap = null;
		
		InputStream is = null;
		try {
			is = context.getResources().openRawResource(resid);
			bitmap = BitmapFactory.decodeStream(is);
			Bitmap old = bitmap;
			bitmap = BitmapUtils.createScaledBitmap(bitmap, size, size, false);
			if (bitmap != old) {
				recycle(bitmap, old);
			}
		} catch (Exception e) {
			//Do nothing
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					//Do nothing
				}
			}
		}
		
		return bitmap;
	}
	
	
	public static Drawable createDrawable(Context context, Bitmap bitmap) {
		return new BitmapDrawable(context.getResources(), bitmap);
	}
	
	
	public static Bitmap invert(Bitmap src) {
		
		Bitmap output = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
		
		int A, R, G, B;
		int pixelColor;
		int height = src.getHeight();
		int width = src.getWidth();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixelColor = src.getPixel(x, y);
				A = Color.alpha(pixelColor);

				R = 255 - Color.red(pixelColor);
				G = 255 - Color.green(pixelColor);
				B = 255 - Color.blue(pixelColor);

				output.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}
		
		recycle(output, src);
		
		return output;
	}
	
	
	public static Bitmap fitInRect(Bitmap source, float rect_width, float rect_height) {
		
		int width = source.getWidth();
		int height = source.getHeight();
		
		float delta_width = width / rect_width;
		float delta_height = height / rect_height;
		
		if (delta_width <= 1 && delta_height <= 1) {
			return source;
		}
		
		Bitmap result;
		
		if (delta_width > delta_height) {
			result = createScaledBitmap(source, (int) (width / delta_width), (int) (height / delta_width), false);
		} else {
			result = createScaledBitmap(source, (int) (width / delta_height), (int) (height / delta_height), false);
		}
		
		recycle(result, source);
		
		return result;
	}
	
	
	public static Bitmap toGrayscale(Bitmap bmpOriginal)
	{        
	    int width, height;
	    height = bmpOriginal.getHeight();
	    width = bmpOriginal.getWidth();    

	    Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, bitmapConfig);
	    
	    Canvas c = new Canvas(bmpGrayscale);
	    Paint paint = new Paint();
	    ColorMatrix cm = new ColorMatrix();
	    cm.setSaturation(0);
	    ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
	    paint.setColorFilter(f);
	    c.drawBitmap(bmpOriginal, 0, 0, paint);
	    
	    
	    return bmpGrayscale;
	}
	
	
	public static Bitmap combineImages_H(Bitmap b1_org, Bitmap b2_org) {
		
		int width = 2 * Math.max(b1_org.getWidth(), b2_org.getWidth());
		int height = Math.max(b1_org.getHeight(), b2_org.getHeight());
		
		Bitmap b1 = createScaledBitmap(b1_org, width / 2, height, false);
		Bitmap b2 = createScaledBitmap(b2_org, width / 2, height, false);
		
		recycle(b1, b1_org);
		recycle(b2, b2_org);
		
		Bitmap result = Bitmap.createBitmap(width, height, bitmapConfig);
		
		Canvas canvas = new Canvas(result);
		
		canvas.drawBitmap(b1, 0f, 0f, null);
		canvas.drawBitmap(b2, width / 2, 0f, null);
		
		recycle(result, b1);
		recycle(result, b2);
		
		return result;
	}

	
	public static Bitmap combineImages_V(Bitmap b1_org, Bitmap b2_org) {
		
		int width = Math.max(b1_org.getWidth(), b2_org.getWidth());
		int height = 2 * Math.max(b1_org.getHeight(), b2_org.getHeight());
		
		Bitmap b1 = createScaledBitmap(b1_org, width, height / 2, false);
		Bitmap b2 = createScaledBitmap(b2_org, width, height / 2, false);
		
		recycle(b1, b1_org);
		recycle(b2, b2_org);
		
		Bitmap result = Bitmap.createBitmap(width, height, bitmapConfig);
		
		Canvas canvas = new Canvas(result);
		
		canvas.drawBitmap(b1, 0f, 0f, null);
		canvas.drawBitmap(b2, 0, height / 2, null);

		recycle(result, b1);
		recycle(result, b2);
		
		return result;
	}
	
	
	public static Bitmap createFromText(int size, String text, int colour) {
		
		Paint paint = new Paint();
		paint.setColor(colour); 
		paint.setTextSize(size);
		
        Rect bounds = new Rect();
        paint.getTextBounds((String) text, 0, text.length(), bounds);
        float pieceXDelta = (size - (bounds.left + bounds.right)) / 2;
        float pieceYDelta = (size - (bounds.top + bounds.bottom)) / 2;
        
        int x = 0;
        int y = 0;
        float xCrd = x + pieceXDelta;
        float yCrd = y + pieceYDelta;
        
		Bitmap bitmap = Bitmap.createBitmap(size, size, bitmapConfig);
		Canvas canvas = new Canvas(bitmap);
        
		canvas.drawText((String) text, xCrd, yCrd, paint);
        
		// new antialised Paint
		/*Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		// text color - #3D3D3D
		paint.setColor(Color.rgb(61, 61, 61));
		// text size in pixels
		paint.setTextSize((int) (14 * scale));
		// text shadow
		paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);

		// draw text to the Canvas center
		Rect bounds = new Rect();
		paint.getTextBounds(gText, 0, gText.length(), bounds);
		int x = (bitmap.getWidth() - bounds.width()) / 2;
		int y = (bitmap.getHeight() + bounds.height()) / 2;

		canvas.drawText(gText, x, y, paint);
		*/
		
		return bitmap;
	}
	
	
	/*public static Bitmap createFromText(String text, int textWidth, int textSize) {
		return createFromText(text, textWidth, textSize, Color.BLACK, Color.WHITE, false);
	}*/
	
	
	public static Bitmap createFromText(String text, int textWidth, int textSize, int colour, int bcolour, boolean hasbackground) {
		
		// Get text dimensions
		TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
		textPaint.setStyle(Paint.Style.FILL);
		textPaint.setColor(colour);
		textPaint.setTextSize(textSize);
		
		StaticLayout mTextLayout = new StaticLayout(text, textPaint, textWidth, Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
		
		// Create bitmap and canvas to draw to
		Bitmap b = Bitmap.createBitmap(textWidth, mTextLayout.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(b);
		
		// Draw background
		if (hasbackground) {
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
			paint.setStyle(Paint.Style.FILL);
			paint.setColor(bcolour);
			c.drawPaint(paint);
		}
		
		// Draw text
		c.save();
		c.translate(0, 0);
		mTextLayout.draw(c);
		c.restore();
	
		return b;
	}
	
	
	public static Bitmap createFromColour(int size, int colour) {
		return createFromColour(size, size, colour);
	}


	public static Bitmap createFromColour(int size_x, int size_y, int colour) {
		
		Paint paint = new Paint();
		paint.setColor(colour); 
		        
		Bitmap bitmap = Bitmap.createBitmap(size_x, size_y, bitmapConfig);
		Canvas canvas = new Canvas(bitmap);
        
		canvas.drawRect(new Rect(0, 0, size_x, size_y), paint);
		
		return bitmap;
	}
	
	
	public static Bitmap createSquareFrom4Colours(int size, int colour1, int colour2, int colour3, int colour4) {
		
		Bitmap b1 = createFromColour(size / 2, colour1);
		Bitmap b2 = createFromColour(size / 2, colour2);
		Bitmap b3 = createFromColour(size / 2, colour3);
		Bitmap b4 = createFromColour(size / 2, colour4);
		
		Bitmap firstRow = combineImages_H(b1, b2);
		Bitmap secondRow = combineImages_H(b4, b3);
		
		Bitmap result = combineImages_V(firstRow, secondRow);
		
		recycle(result, b1);
		recycle(result, b2);
		recycle(result, b3);
		recycle(result, b4);
		recycle(result, firstRow);
		recycle(result, secondRow);
		
		return result;
	}
	
	
	public static Bitmap createLineFrom4Colours(int size, int colour1, int colour2, int colour3, int colour4) {
		
		Bitmap b1 = createFromColour(size, colour1);
		Bitmap b2 = createFromColour(size, colour2);
		Bitmap b3 = createFromColour(size, colour3);
		Bitmap b4 = createFromColour(size, colour4);
		
		Bitmap firstRow = combineImages_H(b1, b2);
		Bitmap secondRow = combineImages_H(b3, b4);
		
		Bitmap result = combineImages_H(firstRow, secondRow);
		
		recycle(result, b1);
		recycle(result, b2);
		recycle(result, b3);
		recycle(result, b4);
		recycle(result, firstRow);
		recycle(result, secondRow);
		
		return result;
	}
	
	
	public static Bitmap createLineFrom4Colours(int size_x, int size_y, int colour1, int colour2, int colour3, int colour4) {
		
		Bitmap b1 = createFromColour(size_x, size_y, colour1);
		Bitmap b2 = createFromColour(size_x, size_y, colour2);
		Bitmap b3 = createFromColour(size_x, size_y, colour3);
		Bitmap b4 = createFromColour(size_x, size_y, colour4);
		
		Bitmap firstRow = combineImages_H(b1, b2);
		Bitmap secondRow = combineImages_H(b3, b4);
		
		Bitmap result = combineImages_H(firstRow, secondRow);
		
		recycle(result, b1);
		recycle(result, b2);
		recycle(result, b3);
		recycle(result, b4);
		recycle(result, firstRow);
		recycle(result, secondRow);
		
		return result;
	}
	
	
    public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
    	
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, new Matrix(), null);
        
        recycle(bmOverlay, bmp1);
        recycle(bmOverlay, bmp2);
        
        return bmOverlay;
    }
    
    
    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        
    	Paint paint = new Paint();
        
    	paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.CENTER);
        
        //int width = (int) (paint.measureText(text) + 0.5f); // round
        //float baseline = (int) (-paint.ascent() + 0.5f); // ascent() is negative
        //int height = (int) (baseline + paint.descent() + 0.5f);
        int width = 500;
        int height = 200;
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, 0, paint);
        
        return image;
    }
}
