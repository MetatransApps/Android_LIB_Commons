package org.metatrans.commons.ui.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

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
	
	
	private static Bitmap.Config DEFAULT_CONFIG = Bitmap.Config.ARGB_8888;

	private static final boolean FILTER = true;
	
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
	
	
	public static Bitmap createScaledBitmap(Bitmap src, int dstWidth, int dstHeight) {
		
		//Fix for rare cases, where dstWidth and/or dstHeight is 0 or less. In this cases the Bitmap API throws IllegalArgumentException.
		if (!Application_Base.getInstance().isTestMode()) {
			if (dstWidth <= 0) {
				dstWidth = 1;
			}
			if (dstHeight <= 0) {
				dstHeight = 1;
			}
		}
		
		Bitmap result = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, FILTER);

		if (result == src) {
			
			result = Bitmap.createBitmap(dstWidth, dstHeight, DEFAULT_CONFIG);
			
			Canvas canvas = new Canvas(result);

			RectF rect_dest = new RectF(0, 0, src.getWidth(), src.getHeight());

			canvas.drawBitmap(src, null, rect_dest, null);
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
			bitmap = BitmapUtils.createScaledBitmap(bitmap, size, size);
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
			result = createScaledBitmap(source, (int) (width / delta_width), (int) (height / delta_width));
		} else {
			result = createScaledBitmap(source, (int) (width / delta_height), (int) (height / delta_height));
		}
		
		recycle(result, source);
		
		return result;
	}
	
	
	public static Bitmap toGrayscale(Bitmap bmpOriginal)
	{        
	    int width, height;
	    height = bmpOriginal.getHeight();
	    width = bmpOriginal.getWidth();    

	    Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, DEFAULT_CONFIG);
	    
	    Canvas c = new Canvas(bmpGrayscale);
	    Paint paint = new Paint();
	    ColorMatrix cm = new ColorMatrix();
	    cm.setSaturation(0);
	    ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
	    paint.setColorFilter(f);
	    c.drawBitmap(bmpOriginal, 0, 0, null);
	    
	    
	    return bmpGrayscale;
	}


	public static Bitmap combineImages_H(Bitmap b1_org, Bitmap b2_org) {

		return combineImages_H(b1_org, b2_org, true);
	}


	public static Bitmap combineImages_H(Bitmap b1_org, Bitmap b2_org, boolean recycle) {
		
		int width = 2 * Math.max(b1_org.getWidth(), b2_org.getWidth());
		int height = Math.max(b1_org.getHeight(), b2_org.getHeight());
		
		Bitmap b1 = createScaledBitmap(b1_org, width / 2, height);
		Bitmap b2 = createScaledBitmap(b2_org, width / 2, height);

		if (recycle) {
			recycle(b1, b1_org);
			recycle(b2, b2_org);
		}

		Bitmap result = Bitmap.createBitmap(width, height, DEFAULT_CONFIG);
		
		Canvas canvas = new Canvas(result);
		
		canvas.drawBitmap(b1, 0f, 0f, null);
		canvas.drawBitmap(b2, width / 2, 0f, null);
		
		recycle(result, b1);
		recycle(result, b2);
		
		return result;
	}


	public static Bitmap extend_H(Bitmap bitmap) {

		int width = 2 * bitmap.getWidth();
		int height = bitmap.getHeight();

		Bitmap result = Bitmap.createBitmap(width, height, DEFAULT_CONFIG);

		Canvas canvas = new Canvas(result);

		canvas.drawBitmap(bitmap, width / 4, 0f, null);

		return result;
	}

	
	public static Bitmap combineImages_V(Bitmap b1_org, Bitmap b2_org) {
		
		int width = Math.max(b1_org.getWidth(), b2_org.getWidth());
		int height = 2 * Math.max(b1_org.getHeight(), b2_org.getHeight());
		
		Bitmap b1 = createScaledBitmap(b1_org, width, height / 2);
		Bitmap b2 = createScaledBitmap(b2_org, width, height / 2);
		
		recycle(b1, b1_org);
		recycle(b2, b2_org);
		
		Bitmap result = Bitmap.createBitmap(width, height, DEFAULT_CONFIG);
		
		Canvas canvas = new Canvas(result);
		
		canvas.drawBitmap(b1, 0f, 0f, null);
		canvas.drawBitmap(b2, 0, height / 2, null);

		recycle(result, b1);
		recycle(result, b2);
		
		return result;
	}


	public static Bitmap combineImages_Overlap(Bitmap b1_org, Bitmap b2_org, int width, int height) {

		Bitmap b1 = createScaledBitmap(b1_org, width, height);
		Bitmap b2 = createScaledBitmap(b2_org, (int) (2 * width / 3f), (int) (2 * height / 3f));

		Bitmap result = Bitmap.createBitmap(width, height, DEFAULT_CONFIG);

		Canvas canvas = new Canvas(result);

		canvas.drawBitmap(b1, 0f, 0f, null);
		canvas.drawBitmap(b2, width / 6, height / 3, null);

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
        
		Bitmap bitmap = Bitmap.createBitmap(size, size, DEFAULT_CONFIG);
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
		        
		Bitmap bitmap = Bitmap.createBitmap(size_x, size_y, DEFAULT_CONFIG);
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


	public static Bitmap overlay_center(Bitmap bmp1, Bitmap bmp2) {

		Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
		Canvas canvas = new Canvas(bmOverlay);
		canvas.drawBitmap(bmp1, new Matrix(), null);

		int shapes_square_width = bmp2.getWidth();
		int shapes_square_height = bmp2.getHeight();
		Rect dst = new Rect();
		dst.left = (bmp1.getWidth() - shapes_square_width) / 2;
		dst.top = (bmp1.getHeight() - shapes_square_height) / 2;
		dst.right = dst.left + shapes_square_width;
		dst.bottom = dst.top + shapes_square_height;
		canvas.drawBitmap(bmp2, null, dst, null);

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


    private static final float MATCH_RATIO = 0.90f;


	public static final Bitmap cropTransparantPart(Bitmap bitmap) {

		int height = bitmap.getHeight();
		int width = bitmap.getWidth();

		int top = 0;
		int left = 0;
		int botton = height;
		int right = width;

		int[] empty = new int[width];
		int[] buffer = new int[width];
		Arrays.fill(empty,0);

		for (int y = 0; y < height; y++) {
			bitmap.getPixels(buffer, 0, width, 0, y, width, 1);
			if (matchRatio(empty, buffer) >= MATCH_RATIO) {
				top = y;
				break;
			}
		}

		for (int y = height - 1; y > top; y--) {
			bitmap.getPixels(buffer, 0, width, 0, y, width, 1);
			if (matchRatio(empty, buffer) >= MATCH_RATIO) {
				botton = y;
				break;
			}
		}

		//System.out.println("bottom=" + botton + ", top=" + top);


		int bufferSize = botton - top;
		empty = new int[bufferSize];
		Arrays.fill(empty,0);
		buffer = new int[bufferSize];

		//System.out.println("bufferSize=" + bufferSize);


		for (int x = 0; x < width; x++) {
			bitmap.getPixels(buffer, 0, 1, x, top + 1, 1, bufferSize);
			if (matchRatio(empty, buffer) >= MATCH_RATIO) {
				left = x;
				break;
			}
		}

		for (int x = width - 1; x > left; x--) {
			bitmap.getPixels(buffer, 0, 1, x, top + 1, 1, bufferSize);
			if (matchRatio(empty, buffer) >= MATCH_RATIO) {
				right = x;
				break;
			}
		}

		Bitmap cropedBitmap = Bitmap.createBitmap(bitmap, left, top, right - left, botton - top);

		bitmap.recycle();

		return cropedBitmap;
	}


	private static float matchRatio(int[] empty, int[] buffer) {

		return Arrays.equals(empty, buffer) ? 0 : 1;
	}

	public static Bitmap generateTransparantPart(Bitmap bitmap, float height_scale, float width_scale, int bottom_margin) {

		if (height_scale <= 0 || height_scale > 1) {

			throw new IllegalStateException("height_scale=" + height_scale);
		}

		if (width_scale <= 0 || width_scale > 1) {

			throw new IllegalStateException("width_scale=" + width_scale);
		}

		int height_org 			= bitmap.getHeight();
		int width_org 			= bitmap.getWidth();

		int height_scaled 		= (int) (height_scale * (height_org - 2 * bottom_margin));
		int width_scaled 		= (int) (height_scale * width_scale * width_org);

		Bitmap bitmap_scaled 	= createScaledBitmap(bitmap, width_scaled, height_scaled);

		Bitmap bitmap_extended 	= Bitmap.createBitmap(width_org, height_org, DEFAULT_CONFIG);

		Canvas canvas 			= new Canvas(bitmap_extended);
		
		canvas.drawBitmap(bitmap_scaled,
							(width_org - width_scaled) / 2,
							(height_org - height_scaled) - bottom_margin,
							null);

		bitmap_scaled.recycle();

		return bitmap_extended;
	}
}
