package com.apps.mobile.android.commons.loading;


import com.apps.mobile.android.commons.app.Application_Base;
import com.apps.mobile.android.commons.events.api.IEvent_Base;
import com.apps.mobile.android.commons.events.api.IEventsManager;
import com.apps.mobile.android.commons.marketing.Activity_Marketing_AppList;
import com.apps.mobile.android.commons.ui.Toast_Base;
import com.apps.mobile.android.commons.web.WebUtils;

import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


public class OnTouchListener_Loading implements OnTouchListener {
	
	
	private View_Loading_Base view;
	
	
	public OnTouchListener_Loading(View_Loading_Base _view) {
		view = _view;
	}
	
	
	@Override
	public boolean onTouch(View _view, MotionEvent event) {
		
		
		int action = event.getAction();

		if (action == MotionEvent.ACTION_DOWN) {
			
			processEvent_DOWN(event);
			
		} else if (action == MotionEvent.ACTION_MOVE) {
			
			processEvent_MOVE(event);
			
		} else if (action == MotionEvent.ACTION_UP) {
			
			processEvent_UP(event);
			
		}
		
		
		view.invalidate();
		
		
		return true;
	}
	
	
	private void processEvent_DOWN(MotionEvent event) {
		
		float x = event.getX();
		float y = event.getY();
		
		
		if (view.isOverStartButton(x, y)) {
			
			view.selectButton_Start();
			
		} else if (view.isOverMoreGamesButton(x, y)) {
			
			view.selectButton_MoreGames();
		} else if (view.isOverSelectColorButton(x, y)) {
			
			view.selectButton_SelectColor();
		} else if (view.isOverSelectLevelButton(x, y)) {
	
			view.selectButton_SelectLevel();

		} else if (view.isOverRateButton(x, y)) {
			
			view.selectButton_Rate();
			
		} else {
			view.pushed(x, y);	
		}
	}
	
	
	private void processEvent_MOVE(MotionEvent event) {
		
		float x = event.getX();
		float y = event.getY();
		
		if (view.isOverStartButton(x, y)) {
			
			view.selectButton_Start();
			
		} else {
			
			view.deselectButton_Start();
			
		}
		
		
		if (view.isOverMoreGamesButton(x, y)) {
			
			view.selectButton_MoreGames();

		} else {
			
			view.deselectButton_MoreGames();
			
		}
		
		
		if (view.isOverSelectColorButton(x, y)) {
			
			view.selectButton_SelectColor();

		} else {
			
			view.deselectButton_SelectColor();
			
		}
		
		
		if (view.isOverSelectLevelButton(x, y)) {
			
			view.selectButton_SelectLevel();

		} else {
			
			view.deselectButton_SelectLevel();
			
		}
		
		if (view.isOverRateButton(x, y)) {
			
			view.selectButton_Rate();
			
		} else {
			
			view.deselectButton_Rate();
			
		}
	}
	
	
	private void processEvent_UP(MotionEvent event) {
		
		
		float x = event.getX();
		float y = event.getY();
		
		
		view.deselectButton_Start();
		view.deselectButton_MoreGames();
		view.deselectButton_SelectColor();
		view.deselectButton_SelectLevel();
		view.deselectButton_Rate();
		
		view.invalidate();
		
		
		if (view.isOverStartButton(x, y)) {
			Intent i = new Intent(view.getContext(), ((Activity_Loading_Base)view.getContext()).getNextActivityClass());
			view.getContext().startActivity(i);
		}
		
		if (view.isOverMoreGamesButton(x, y)) {
			Intent i = new Intent(view.getContext(), Activity_Marketing_AppList.class);
			view.getContext().startActivity(i);
		}
		
		if (view.isOverSelectColorButton(x, y)) {
			Intent i = new Intent(view.getContext(), ((Activity_Loading_Base)view.getContext()).getActivityClass_Menu1());
			view.getContext().startActivity(i);
		}
		
		if (view.isOverSelectLevelButton(x, y)) {
			Class menu2 = ((Activity_Loading_Base)view.getContext()).getActivityClass_Menu2();
			if (menu2 != null) {
				Intent i = new Intent(view.getContext(), menu2);
				view.getContext().startActivity(i);
			} else {
				Toast_Base.showToast_InBottom_Long(view.getContext(), "Under construction");
			}
		}
		
		if (view.isOverRateButton(x, y)) {
			
	    	WebUtils.openApplicationStorePage((Activity) view.getContext(), Application_Base.getInstance().getApp_Me());
	    	
	    	try {
	    		IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
	    		eventsManager.register(Application_Base.getInstance(), eventsManager.create(IEvent_Base.MENU_OPERATION, IEvent_Base.MENU_OPERATION_CHECKFORUPDATES,
					"MENU_OPERATION", "CHECKFORUPDATES"));
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
		} 
	}
}
