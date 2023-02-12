package org.metatrans.commons.engagement.leaderboards;


import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.cfg.colours.IConfigurationColours;
import org.metatrans.commons.engagement.IEngagementProvider;
import org.metatrans.commons.events.api.IEvent_Base;
import org.metatrans.commons.events.api.IEventsManager;
import org.metatrans.commons.ui.ButtonAreaClick_Image;
import org.metatrans.commons.ui.IButtonArea;
import org.metatrans.commons.ui.utils.BitmapUtils;
import org.metatrans.commons.ui.utils.DrawingUtils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


public abstract class View_Achievements_And_Leaderboards_Base extends View implements OnTouchListener {


	private boolean initialized;
	
	private RectF rectf_main;
	private RectF rectangle_icon;
	private RectF rectangle_button_leaderboards;
	private RectF rectangle_button_achievements;

	private IButtonArea buttonarea_icon;
	private IButtonArea buttonarea_button_leaderboards;
	private IButtonArea buttonarea_button_achievements;
	
	protected Paint paint_background;
	
	private IEngagementProvider provider;
	private IConfigurationColours coloursCfg;
	
	private IViewActivator activator;
	
	
	public View_Achievements_And_Leaderboards_Base(Context context, RectF _rectf_main, IEngagementProvider _provider, IConfigurationColours _coloursCfg, IViewActivator _activator) {
		
		super(context);
		
		provider 						= _provider;
		
		coloursCfg 						= _coloursCfg;
		
		rectf_main 						= _rectf_main; 
		
		rectangle_icon 					= new RectF();
		rectangle_button_leaderboards 	= new RectF();
		rectangle_button_achievements 	= new RectF();
		
		paint_background 				= new Paint();
		
		activator = _activator;
		
		setOnTouchListener(this);
	}
	
	
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		if (!initialized) {

			int MARGIN = 7;

			int count_buttons = 3;

			rectangle_icon.left = rectf_main.left + MARGIN;
			rectangle_icon.right = rectangle_icon.left + (1 * (rectf_main.right - rectf_main.left - (count_buttons + 1) * MARGIN)) / count_buttons;
			rectangle_icon.top = rectf_main.top + MARGIN;
			rectangle_icon.bottom = rectf_main.bottom - MARGIN;

			rectangle_button_leaderboards.left = rectangle_icon.right + MARGIN;
			rectangle_button_leaderboards.right = rectangle_button_leaderboards.left + (1 * (rectf_main.right - rectf_main.left - (count_buttons + 1) * MARGIN)) / count_buttons;
			rectangle_button_leaderboards.top = rectf_main.top + MARGIN;
			rectangle_button_leaderboards.bottom = rectf_main.bottom - MARGIN;

			rectangle_button_achievements.left = rectangle_button_leaderboards.right + MARGIN;
			rectangle_button_achievements.right = rectf_main.right - MARGIN;
			rectangle_button_achievements.top = rectf_main.top + MARGIN;
			rectangle_button_achievements.bottom = rectf_main.bottom - MARGIN;


			buttonarea_icon =  new ButtonAreaClick_Image(rectangle_icon,
					BitmapUtils.fromResource(getContext(), getResID_Icon_Leaderboard()),
					coloursCfg.getColour_Delimiter(),
					coloursCfg.getColour_Square_White(),
					false
					);

			buttonarea_button_leaderboards =  new ButtonAreaClick_Image(rectangle_button_leaderboards,
					BitmapUtils.fromResource(getContext(), getResID_Button_OpenLeaderboard()),
					coloursCfg.getColour_Square_ValidSelection(),
					coloursCfg.getColour_Square_MarkingSelection(),
					false
					);

			buttonarea_button_achievements =  new ButtonAreaClick_Image(rectangle_button_achievements,
					BitmapUtils.fromResource(getContext(), getResID_Button_OpenAchievements()),
					coloursCfg.getColour_Square_ValidSelection(),
					coloursCfg.getColour_Square_MarkingSelection(),
					false
					);


			initialized = true;

			setMeasuredDimension( (int) (rectf_main.right - rectf_main.left), (int) (rectf_main.bottom - rectf_main.top));
		}
	}
	
	
	public abstract int getResID_Icon_Leaderboard();
	
	public abstract int getResID_Button_OpenLeaderboard();
	
	public abstract int getResID_Button_OpenAchievements();
	
	
	@Override
	protected synchronized void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
		
		paint_background.setColor(coloursCfg.getColour_Delimiter());

		DrawingUtils.drawRoundRectangle(canvas, paint_background, rectf_main, 50);
		
		buttonarea_icon.draw(canvas);
		
		buttonarea_button_leaderboards.draw(canvas);
		
		buttonarea_button_achievements.draw(canvas);
		
		//System.out.println("View_Achievements_And_Leaderboards_Base> onDraw in rect=" + rectf_main);
	}
	
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		
		if (!activator.isActive()) {
			return false;
		}
		
		float x = event.getX();
		float y = event.getY();
		
		if (!rectf_main.contains(x, y)) {
			return false;
		}
		
		synchronized (this) {
			
			int action = event.getAction();

			if (action == MotionEvent.ACTION_DOWN) {

				processEvent_DOWN(event);

			} else if (action == MotionEvent.ACTION_MOVE) {

				processEvent_MOVE(event);
				
			} else if (action == MotionEvent.ACTION_UP
					|| action == MotionEvent.ACTION_CANCEL) {

				processEvent_UP(event);

			}
		}
		
		invalidate();
		
		return true;
	}
	
	
	private boolean isOverButton_OpenLeaderboard(float x, float y) {
		return rectangle_button_leaderboards.contains(x, y);
	}
	
	
	private boolean isOverButton_OpenAchievements(float x, float y) {
		return rectangle_button_achievements.contains(x, y);
	}
	
	
	private void processEvent_DOWN(MotionEvent event) {
		
		float x = event.getX();
		float y = event.getY();
		
		if (isOverButton_OpenLeaderboard(x, y)) {
			
			buttonarea_button_leaderboards.select();

		} else if (isOverButton_OpenAchievements(x, y)) {
			
			buttonarea_button_achievements.select();
		}
	}
	
	
	private void processEvent_MOVE(MotionEvent event) {
		
		float x = event.getX();
		float y = event.getY();
		
		if (isOverButton_OpenLeaderboard(x, y)) {
			
			buttonarea_button_leaderboards.select();

		} else {
			
			buttonarea_button_leaderboards.deselect();
		}
		
		
		if (isOverButton_OpenAchievements(x, y)) {
			
			buttonarea_button_achievements.select();
			
		} else {
			
			buttonarea_button_achievements.deselect();
			
		}
	}
	
	
	private void processEvent_UP(MotionEvent event) {
		
		float x = event.getX();
		float y = event.getY();
		
		buttonarea_button_leaderboards.deselect();
		buttonarea_button_achievements.deselect();
		
		if (isOverButton_OpenLeaderboard(x, y)) {
			
			int modeID = Application_Base.getInstance().getUserSettings().modeID;

			provider.getLeaderboardsProvider().openLeaderboard_LocalOnly(modeID);
			provider.getLeaderboardsProvider().openLeaderboard(modeID);
			
        	IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
    		eventsManager.register(getContext(), IEvent_Base.EVENT_MENU_OPERATION_LEADERBOARDS);
			
		} else if (isOverButton_OpenAchievements(x, y)) {
			
			provider.getAchievementsProvider().openAchievements();
			
        	IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
    		eventsManager.register(getContext(), IEvent_Base.EVENT_MENU_OPERATION_ACHIEVEMENTS);
		}
	}
}
