package org.metatransapps.commons.ui;


import org.metatransapps.commons.ui.utils.DrawingUtils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Align;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.Layout.Alignment;


public class TextArea implements IButtonArea {
	
	private RectF rect;
	private String text;

	private boolean drawTextOnly;
	
	protected int colour_area;
	private int colour_text;

	private int left_right_border = 3;
	
	private StaticLayout box;
	private float x;
	private float y;
	

	private Paint paint;
	private TextPaint textPaint;
	
	private boolean resizetext;
	

	public TextArea(RectF _rect, String _text, int _colour_area, int _colour_text) {
		this(_rect, _text, false, _colour_area, _colour_text, true);
	}

	public TextArea(RectF _rect, boolean _drawTextOnly, String _text, int _colour_area, int _colour_text) {
		this(_rect, _text, _drawTextOnly, _colour_area, _colour_text, true);
	}

	public TextArea(RectF _rect, String _text, int _colour_text) {
		this(_rect, _text, true, -1, _colour_text, false);
	}
	
	/*public TextArea(RectF _rect, String _text, int _colour_area, int _colour_text, boolean _resizetext) {
		this(_rect, _text, false, _colour_area, _colour_text, _resizetext);
	}*/
	
	
	protected TextArea(RectF _rect, String _text, boolean _drawTextOnly, int _colour_area, int _colour_text, boolean _resizetext) {
		
		rect = _rect;
		text = _text;
		
		drawTextOnly = _drawTextOnly;
		
		colour_area = _colour_area;
		colour_text = _colour_text;
		
		resizetext = _resizetext;
		
		textPaint = new TextPaint();
		textPaint.setColor(colour_text);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setAntiAlias(true);
		
		paint = new Paint();
		paint.setColor(colour_area);
		
		init();
	}
	
	
	public void setText(String _text) {
		
		if (text != null) {
			if (text.equals(_text)) {
				return;
			}
		}
		
		text = _text;
		
		if (resizetext) {
			init();
		} else {
			int width = (int) getWidth();
			if (width <= 0) {//Bugfixing for rare cases, StaticLayout/android.text.Layout.<init> throws IllegalArgumentException when the width < 0
				return;
			}
			
			box = new StaticLayout(text, textPaint, width, getAlignment(text), 1F, 0F, true);
			//box.
		}
	}
	
	
	private void init() {
		
		//System.out.println("Creating TEXT AREA: " + "rectangle=" + rect);
		
		int height = (int) (rect.bottom - rect.top);
		int width = (int) getWidth();
		if (width <= 0) {//Bugfixing for rare cases, StaticLayout/android.text.Layout.<init> throws IllegalArgumentException when the width < 0
			return;
		}
		
		int textSize = 100;
		do {
			textPaint.setTextSize(textSize);
			textSize -= 1;
			//System.out.println("TEXT AREA creating: x=" + x + ", y=" + y + ", textsize=" + textPaint.getTextSize() + ", rectangle=" + rect);
			
			box = new StaticLayout(text, textPaint, width, getAlignment(text), 1F, 0F, true);
		} while (textSize > 0 && (box.getHeight() > height || textPaint.measureText(text) > width));
		
		x = rect.left + width / 2 + left_right_border;
		y = rect.bottom - height / 2 - box.getHeight() / 2;
		
		//System.out.println("TEXT AREA created: x=" + x + ", y=" + y + ", textsize=" + textPaint.getTextSize() + ", rectangle=" + rect);
	}
	
	
	public void draw(Canvas canvas) {
		
		if (getWidth() <= 0) {//Bugfixing for rare cases, StaticLayout/android.text.Layout.<init> throws IllegalArgumentException when the width < 0
			return;
		}
		
		if (!drawTextOnly) {
			DrawingUtils.drawRoundTextArea(canvas, paint, rect);
		}
		
		canvas.save();
		canvas.translate(x, y);
		
		box.draw(canvas);
		
		canvas.restore();
	}
	
	
	private float getWidth() {
		return rect.right - rect.left - 2 * left_right_border;
	}
	
	
	public void setColour_Text(int colour_text) {
		
		if (this.colour_text == colour_text) {
			return;
		}
		
		this.colour_text = colour_text;
		textPaint.setColor(colour_text);
	}
	
	public void setColour_Area(int colour_area) {
		
		//if (this.colour_area == colour_area) {
		//	return;
		//}
		
		//this.colour_area = colour_area;
		
		paint.setColor(colour_area);
	}
	
	
	public String getText() {
		return text;
	}
	
	
	@Override
	public void select() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void deselect() {
		// TODO Auto-generated method stub
		
	}
	
	
	private static Alignment getAlignment(String text) {
	    for (char character : text.toCharArray()) {
	        if (Character.UnicodeBlock.of(character) == Character.UnicodeBlock.ARABIC) {
	            return Alignment.ALIGN_OPPOSITE;
	        }
	    }
	    return Alignment.ALIGN_NORMAL;
	}
}
