package org.metatrans.commons.loading;


import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.events.api.IEvent_Base;
import org.metatrans.commons.events.api.IEventsManager;
import org.metatrans.commons.web.WebUtils;

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
	}
	
	
	private void processEvent_UP(MotionEvent event) {
		
		
		float x = event.getX();
		float y = event.getY();
		
		
		view.deselectButton_Start();


		view.invalidate();
		
		
		if (view.isOverStartButton(x, y)) {
			Intent i = new Intent(view.getContext(), ((Activity_Loading_Base)view.getContext()).getNextActivityClass());
			view.getContext().startActivity(i);
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
