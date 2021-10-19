package org.metatrans.commons.ui.utils;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;


public class DrawingUtils {
	
	
	public static void drawRoundRectangle(Canvas canvas, Paint paint, RectF rect, int radius) {
		float radx = radius;//rect.width() / (float) 12;
		float rady = radius;//rect.height() / (float) 12;
		canvas.drawRoundRect(rect, radx, rady, paint);
	}
	
	
	public static void drawRoundRectangle(Canvas canvas, Paint paint, RectF rect) {
		drawRoundRectangle(canvas, paint, rect, 0);
	}


	private static void drawRoundTextArea(Canvas canvas, Paint paint, RectF rect, int radius) {
		float rady = radius;//rect.height() / (float) 12;
		float radx = radius;//rect.width() / (float) 12;
		canvas.drawRoundRect(rect, radx, rady, paint);
	}

	public static void drawRoundTextArea(Canvas canvas, Paint paint, RectF rect) {
		drawRoundTextArea(canvas, paint, rect, 50);
	}
	
	
	public static void drawInField(Canvas canvas, Paint paint, float squareSize, float x, float y, Bitmap bitmap) {
        canvas.drawBitmap(bitmap, x, y, paint);
	}
	
	
	public static void drawInCenter(Canvas canvas, Paint paint, float squareSize, float x, float y, Bitmap bitmap) {
        canvas.drawBitmap(bitmap, x - squareSize / 2, y - squareSize / 2, paint);
	}
}
