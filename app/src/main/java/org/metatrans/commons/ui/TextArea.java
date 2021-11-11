package org.metatrans.commons.ui;


import org.metatrans.commons.ui.utils.DrawingUtils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Align;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.Layout.Alignment;


public class TextArea implements IButtonArea {


	private static final int LEFT_MARGIN = 5;


	private RectF rect;

	private String text;

	private boolean drawTextOnly;
	
	protected int colour_area;

	private int colour_text;
	
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

			//Bugfixing for rare cases, StaticLayout/android.text.Layout.<init> throws IllegalArgumentException when the width < 0
			if (width <= 0) {

				return;
			}

			StaticLayout.Builder builder = StaticLayout.Builder.obtain(text, 0, text.length(), textPaint, width)
					.setAlignment(getAlignment(text))
					.setLineSpacing(0F, 1F)
					.setIncludePad(true)
					.setMaxLines(3);

			box = builder.build();

			//box = new StaticLayout(text, textPaint, width, getAlignment(text), 1F, 0F, true);
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

		boolean is_1_line = !text.contains("\r\n");

		do {

			textPaint.setTextSize(textSize);

			StaticLayout.Builder builder = StaticLayout.Builder.obtain(text, 0, text.length(), textPaint, width)
					.setAlignment(getAlignment(text))
					.setLineSpacing(0F, 1F)
					.setIncludePad(true)
					.setMaxLines(3);

			box = builder.build();

			//box = new StaticLayout(text, textPaint, width, getAlignment(text), 1F, 0F, true);

			textSize -= 1;
			//System.out.println("TEXT AREA creating: x=" + x + ", y=" + y + ", textsize=" + textPaint.getTextSize() + ", rectangle=" + rect);

		} while (textSize > 0
				&& (box.getHeight() > height || textPaint.measureText(text) > (is_1_line ? width : 2 * width)));


		x = rect.left + width / 2 + LEFT_MARGIN;

		y = rect.bottom - height / 2 - box.getHeight() / 2;
		
		//System.out.println("TEXT AREA created: x=" + x + ", y=" + y + ", textsize=" + textPaint.getTextSize() + ", rectangle=" + rect);
	}
	
	
	public void draw(Canvas canvas) {

		//Bugfixing for rare cases, StaticLayout/android.text.Layout.<init> throws IllegalArgumentException when the width < 0
		if (getWidth() <= 0) {

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
		return rect.right - rect.left -2 * LEFT_MARGIN;
	}
	
	
	public void setColour_Text(int colour_text) {
		
		if (this.colour_text == colour_text) {

			return;
		}
		
		this.colour_text = colour_text;

		textPaint.setColor(colour_text);
	}


	public void setColour_Area(int colour_area) {

		paint.setColor(colour_area);
	}
	
	
	public String getText() {
		return text;
	}

	
	private static Alignment getAlignment(String text) {
	    for (char character : text.toCharArray()) {
	        if (Character.UnicodeBlock.of(character) == Character.UnicodeBlock.ARABIC) {
	            return Alignment.ALIGN_OPPOSITE;
	        }
	    }
	    return Alignment.ALIGN_NORMAL;
	}


	@Override
	public void select() {
		//Do nothing
	}


	@Override
	public void deselect() {
		//Do nothing
	}
}
