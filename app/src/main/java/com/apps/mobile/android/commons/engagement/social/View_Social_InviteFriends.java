package com.apps.mobile.android.commons.engagement.social;


import com.apps.mobile.android.commons.R;
import com.apps.mobile.android.commons.cfg.colours.IConfigurationColours;
import com.apps.mobile.android.commons.engagement.ISocialProvider;
import com.apps.mobile.android.commons.ui.ButtonAreaClick;
import com.apps.mobile.android.commons.ui.IButtonArea;
import com.apps.mobile.android.commons.ui.utils.DrawingUtils;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


public class View_Social_InviteFriends extends View implements OnTouchListener {
	
	
	private boolean initialized;
	
	private RectF rectangle_button;
	
	private IButtonArea buttonarea_button;
	
	protected Paint paint_background;
	
	private ISocialProvider provider;
	private IConfigurationColours coloursCfg;
	
	
	public View_Social_InviteFriends(Activity context, RectF _rectf_main, ISocialProvider _provider, IConfigurationColours _coloursCfg) {
		
		super(context);
		
		System.out.println("View_Social_InviteFriends: constructor _rectf_main=" + _rectf_main);
		
		rectangle_button 	= _rectf_main;
		
		provider 			= _provider;
		
		coloursCfg 			= _coloursCfg;
		
		paint_background 	= new Paint();
		
		setOnTouchListener(this);
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		//System.out.println("View_Social_InviteFriends: onMeasure initialized=" + initialized);
		
		if (!initialized) {
			
			String text = " " + getContext().getString(R.string.label_invite) + " ";
			buttonarea_button =  new ButtonAreaClick(rectangle_button, text,
					//coloursCfg.getColour_Delimiter(), coloursCfg.getColour_Square_White(), coloursCfg.getColour_Square_ValidSelection());
					coloursCfg.getColour_Square_ValidSelection(),
					coloursCfg.getColour_Square_Black(),
					coloursCfg.getColour_Square_MarkingSelection());
			
			initialized = true;
		}
		
		//System.out.println("View_Social_InviteFriends: onMeasure out");
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		//System.out.println("View_Social_InviteFriends: onDraw");
		
		super.onDraw(canvas);
		
		paint_background.setColor(coloursCfg.getColour_Delimiter());
		DrawingUtils.drawRoundTextArea(canvas, paint_background, rectangle_button);
		
		
		buttonarea_button.draw(canvas);
	}
	
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		
		//System.out.println("View_Social_InviteFriends: onTouch ");
		
		float x = event.getX();
		float y = event.getY();
		
		if (!rectangle_button.contains(x, y)) {
			
			buttonarea_button.deselect();
			
			invalidate();
			
			return false;
			
		} else {
		
			
			int action = event.getAction();
			
			if (action == MotionEvent.ACTION_DOWN) {
				
				processEvent_DOWN(event);
				
			} else if (action == MotionEvent.ACTION_MOVE) {
				
				processEvent_MOVE(event);
				
			} else if (action == MotionEvent.ACTION_UP) {
				
				processEvent_UP(event);
				
			}
			
			invalidate();
			
			
			return true;
		}
	}
	
	
	private boolean isOverButton_InviteFriends(float x, float y) {
		return rectangle_button.contains(x, y);
	}
	
	
	private void processEvent_DOWN(MotionEvent event) {
		
		float x = event.getX();
		float y = event.getY();
		
		if (isOverButton_InviteFriends(x, y)) {
			
			buttonarea_button.select();

		}
	}
	
	
	private void processEvent_MOVE(MotionEvent event) {
		
		float x = event.getX();
		float y = event.getY();
		
		if (isOverButton_InviteFriends(x, y)) {
			
			buttonarea_button.select();

		} else {
			
			buttonarea_button.deselect();
		}
	}
	
	
	private void processEvent_UP(MotionEvent event) {
		
		float x = event.getX();
		float y = event.getY();
		
		buttonarea_button.deselect();
		
		if (isOverButton_InviteFriends(x, y)) {
			
			provider.openInviteDialog();
		}
	}
}
