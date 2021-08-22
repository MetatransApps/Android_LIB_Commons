package com.apps.mobile.android.commons.ui.utils;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;


public class DrawingUtils {
	
	
	public static void drawRoundRectangle(Canvas canvas, Paint paint, RectF rect, int radius) {
		canvas.drawRoundRect(rect, radius, radius, paint);
	}
	
	
	public static void drawRoundRectangle(Canvas canvas, Paint paint, RectF rect) {
		float rady = rect.height() / (float) 12;
		float radx = rect.width() / (float) 12;
		canvas.drawRoundRect(rect, radx, rady, paint);
	}
	
	
	public static void drawRoundTextArea(Canvas canvas, Paint paint, RectF rect) {
		float rady = rect.height() / (float) 3;
		float radx = rect.width() / (float) 3;
		canvas.drawRoundRect(rect, radx, rady, paint);
	}
	
	
	public static void drawInField(Canvas canvas, Paint paint, float squareSize, float x, float y, Bitmap bitmap) {
        canvas.drawBitmap(bitmap, x, y, paint);
	}
	
	
	public static void drawInCenter(Canvas canvas, Paint paint, float squareSize, float x, float y, Bitmap bitmap) {
        canvas.drawBitmap(bitmap, x - squareSize / 2, y - squareSize / 2, paint);
	}
}
