package org.metatrans.commons.ui;


import org.metatrans.commons.ui.utils.DrawingUtils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;


public abstract class ImageArea implements IButtonArea {
	
	
	private RectF rect;

	private boolean drawImageOnly;
	
	protected int colour_area;
	
	private Bitmap bitmap;
	
	private Rect src;

	private RectF dest;
	
	private Paint paint;
	
	private boolean scaleImage;
	
	
	public ImageArea(RectF _rect, Bitmap _bitmap, int _colour_area) {
		this(_rect, _bitmap, false, _colour_area, true);
	}
	
	public ImageArea(RectF _rect, Bitmap _bitmap, int _colour_area, boolean _scaleImage) {
		this(_rect, _bitmap, false, _colour_area, _scaleImage);
	}
	
	
	protected ImageArea(RectF _rect, Bitmap _bitmap, boolean _drawImageOnly, int _colour_area, boolean _scaleImage) {
		
		rect = _rect;
		
		bitmap = _bitmap;
		
		drawImageOnly = _drawImageOnly;
		
		colour_area = _colour_area;
		
		scaleImage = _scaleImage;
		
		paint = new Paint();

		paint.setColor(colour_area);

		int MARGIN =  1;//(int) Math.max(rect.height() / 10, rect.width()/ 10);
		
		src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		
		if (scaleImage) {
			
			dest = new RectF(rect.left + MARGIN, rect.top + MARGIN,
					rect.right - MARGIN, rect.bottom - MARGIN);
		} else {
			
			float x_area = rect.width();
			float y_area = rect.height();
			float x_image = Math.min(x_area, bitmap.getWidth());
			float y_image = Math.min(y_area, bitmap.getHeight());
			
			float x_rate = x_image / x_area;
			float y_rate = y_image / y_area;
			
			if (x_rate > y_rate) {
				x_image /= x_rate;
				y_image /= x_rate;
			} else {
				x_image /= y_rate;
				y_image /= y_rate;
			}
			float left = rect.left + ((rect.right - rect.left) - x_image) / 2;
			float top = rect.top + ((rect.bottom - rect.top) - y_image) / 2;
			float right = left + x_image;
			float bottom = top + y_image;
			
			dest = new RectF(left + MARGIN, top + MARGIN, right - MARGIN, bottom - MARGIN);
		}
	}


	public void setColour_Area(int colour_area) {

		paint.setColor(colour_area);
	}


	public void draw(Canvas canvas) {

		if (!drawImageOnly) {

			DrawingUtils.drawRoundTextArea(canvas, paint, rect);
		}
		
		canvas.save();
		canvas.drawBitmap(bitmap, src, dest, paint);
		canvas.restore();
		
	}
}
