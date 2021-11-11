package org.metatrans.commons.loading;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.metatrans.commons.R;
import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.cfg.colours.ConfigurationUtils_Colours;
import org.metatrans.commons.events.api.IEvent_Base;
import org.metatrans.commons.events.api.IEventsManager;
import org.metatrans.commons.loading.logic.MovingEntry;
import org.metatrans.commons.ui.ButtonAreaClick;
import org.metatrans.commons.ui.ButtonAreaClick_Image;
import org.metatrans.commons.ui.IButtonArea;
import org.metatrans.commons.ui.TextArea;
import org.metatrans.commons.ui.utils.BitmapUtils;
import org.metatrans.commons.ui.utils.DrawingUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;


public abstract class View_Loading_Base extends Activity_Loading_Base.ViewWithLeaderBoard implements View.OnTouchListener {


	private static final int MAX_ITERS = 4;


	private List<MovingEntry> entries;

	
	private RectF rectf_main;

	private RectF rectf_leaderboards;
	private RectF rectf_button_start;
	
	private RectF rectf_button_googleplus;
	private RectF rectf_button_rate_review;
	
	private TextArea textarea_label_loading;
	private IButtonArea buttonarea_start;
	
	protected Paint paint_background;
	protected Paint paint_images;
	
	private boolean sent_event_one_stoped = false;
	private boolean sent_event_all_stoped = false;
	
	
	public View_Loading_Base(Context context) {
		
		super(context);
		
		rectf_main 					= new RectF();
		rectf_button_start 			= new RectF();
		
		rectf_leaderboards 			= new RectF();
		
		rectf_button_googleplus		= new RectF();
		rectf_button_rate_review	= new RectF();
		
		paint_background 			= new Paint();
		paint_images 				= new Paint();
		
		entries 					= new Vector<MovingEntry>();
	}
	
	
	public abstract void initPiecesBitmaps();

	protected abstract Bitmap[] getCommonBitmaps();

	protected abstract Bitmap getBitmapBackground();//Can be null
	
	
	public RectF getRectangle_LeaderBoards() {
		return rectf_leaderboards;
	}
	

	
	//@Override
	protected void registerEvent_AllStopped() {
		IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
		eventsManager.register(((Activity)getContext()), eventsManager.create(IEvent_Base.LOADING, IEvent_Base.LOADING_STOPPED_PIECES, "LOADING", "LOADING_STOPPED_PIECES"));
	}
	
	
	//@Override
	protected void registerEvent_1Stopped() {
		IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
		eventsManager.register(((Activity)getContext()), eventsManager.create(IEvent_Base.LOADING, IEvent_Base.LOADING_STOPPED_PIECE, "LOADING", "LOADING_STOPPED_PIECE"));
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		rectf_main.left = 0;
		rectf_main.right = getMeasuredWidth();
		rectf_main.top = 0;
		rectf_main.bottom = getMeasuredHeight();

		int MARGIN = 10;
		int buttons_width = getMeasuredWidth() / 2;
		int buttons_height =  getMeasuredHeight() / 11;
		int buttons_distance =  (getMeasuredHeight() - 6 * buttons_height) / 7;

		if (getMeasuredWidth() > getMeasuredHeight()) {
			buttons_height *= 1.5;
			buttons_distance =  (getMeasuredHeight() - 6 * buttons_height) / 7;
		}

		rectf_button_start.left = (rectf_main.right - rectf_main.left) / 2 - buttons_width / 2;
		rectf_button_start.right = (rectf_main.right - rectf_main.left) / 2 + buttons_width / 2;
		rectf_button_start.top = (rectf_main.bottom - rectf_main.top) / 2 - buttons_height / 2;
		rectf_button_start.bottom = (rectf_main.bottom - rectf_main.top) / 2 + 2 * buttons_height / 2;


		float googleplus_button_half_size = buttons_width / 6;

		if (getMeasuredHeight() > getMeasuredWidth()) {

			rectf_leaderboards.left = rectf_button_start.left;
			rectf_leaderboards.right = rectf_button_start.right;
			rectf_leaderboards.top = rectf_button_start.top / 2 - buttons_height / 2;
			rectf_leaderboards.bottom = rectf_leaderboards.top + buttons_height;

		} else {

			googleplus_button_half_size /= 2;

			rectf_leaderboards.left = rectf_button_start.left + 1 * (rectf_button_start.right - rectf_button_start.left) / 4;
			rectf_leaderboards.right = rectf_leaderboards.left + 2 * (rectf_button_start.right - rectf_button_start.left) / 4;
			rectf_leaderboards.top = rectf_button_start.top / 2 - buttons_height / 2;
			rectf_leaderboards.bottom = rectf_leaderboards.top + buttons_height;
		}


		rectf_button_googleplus.left = rectf_leaderboards.left / 2 - googleplus_button_half_size;
		rectf_button_googleplus.top = rectf_button_start.top;
		rectf_button_googleplus.right = rectf_leaderboards.left / 2 + googleplus_button_half_size;
		rectf_button_googleplus.bottom = rectf_button_start.bottom;

		rectf_button_rate_review.left = rectf_leaderboards.right + (rectf_main.right - rectf_leaderboards.right) / 2 - googleplus_button_half_size;
		rectf_button_rate_review.top = rectf_button_start.top + (rectf_button_start.bottom - rectf_button_start.top) / 2 - googleplus_button_half_size;
		rectf_button_rate_review.right = rectf_leaderboards.right + (rectf_main.right - rectf_leaderboards.right) / 2 + googleplus_button_half_size;
		rectf_button_rate_review.bottom = rectf_button_start.top + (rectf_button_start.bottom - rectf_button_start.top) / 2 + googleplus_button_half_size;

		createButtons();

		this.setMeasuredDimension( (int) (rectf_main.right - rectf_main.left), (int) (rectf_main.bottom - rectf_main.top) );


		initPiecesBitmaps();


		setOnTouchListener(this);
	}
	
	
	private void createButtons() {
		
		textarea_label_loading = new TextArea(rectf_button_start, false, " " + getLoadingActivity().getString(getLoadingActivity().getText_Loading()) + " ",
				getLoadingActivity().getColoursCfg().getColour_Square_Black(),
				getLoadingActivity().getColoursCfg().getColour_Square_ValidSelection());

		buttonarea_start = new ButtonAreaClick_Image(rectf_button_start,
				BitmapUtils.fromResource(getContext(), R.drawable.ic_action_playback_play_white),
				getLoadingActivity().getColoursCfg().getColour_Square_ValidSelection(),
				getLoadingActivity().getColoursCfg().getColour_Square_MarkingSelection(),
				false
		);
	}
	
	
	public float getSquareSize() {
		return Math.min(rectf_main.width(), rectf_main.height()) / 5;
	}
	
	
	public boolean isOverStartButton(float x, float y) {

		return rectf_button_start.contains(x, y);
	}
	
	
	public void selectButton_Start() {

		buttonarea_start.select();
	}


	public void deselectButton_Start() {
		
		buttonarea_start.deselect();
	}
	
	
	public synchronized void pushed(float x, float y) {
		
		RectF rectangle_pushed = new RectF();
		
		rectangle_pushed.left = x - getSquareSize() / 2;
		rectangle_pushed.right = x + getSquareSize() / 2;
		rectangle_pushed.top = y - getSquareSize() / 2;
		rectangle_pushed.bottom = y + getSquareSize() / 2;
		
		
		MovingEntry minclicks_entry = null;
		
		for (int i=0; i<entries.size(); i++) {
			
			MovingEntry entry = entries.get(i);
			
			if (rectangle_pushed.contains(entry.coordinates.x, entry.coordinates.y)) {	
				//Clicked
				if (minclicks_entry == null) {
					minclicks_entry = entry;
				} else {
					if (minclicks_entry.clicks > entry.clicks) {
						minclicks_entry = entry;
					}
				}
			}
		}
		
		if (minclicks_entry != null) {
			
			minclicks_entry.clicks++;
			
			entries.remove(minclicks_entry);
			entries.add(minclicks_entry);
		}

		
		if (!sent_event_one_stoped) {
			
			boolean one_stoped = false;
			for (int i=0; i<entries.size(); i++) {
				MovingEntry entry = entries.get(i);
				if (entry.clicks >= MAX_ITERS) {
					one_stoped = true;
					break;
				}
			}
			
			if (one_stoped) {
				sent_event_one_stoped = true;
				registerEvent_1Stopped();
			}
		}
		
		
		if (!sent_event_all_stoped) {
			
			boolean all_stoped = true;
			for (int i=0; i<entries.size(); i++) {
				MovingEntry entry = entries.get(i);
				if (entry.clicks < MAX_ITERS) {
					all_stoped = false;
					break;
				}
			}
			
			if (all_stoped) {
				sent_event_all_stoped = true;
				registerEvent_AllStopped();
			}
		}
	}
	
	
	protected void createEntry(Bitmap bitmap) {
		
		List<Bitmap> bitmaps = new ArrayList<Bitmap>();
		bitmaps.add(bitmap);
		bitmaps.add(bitmap);
		bitmaps.add(bitmap);
		for (int i=0; i<getCommonBitmaps().length; i++) {
			bitmaps.add(getCommonBitmaps()[i]);
		}
		
		float padding = 0.1f;
		float initial_x = (float) (padding * rectf_main.width() + Math.random() * (1 - 2 * padding) * rectf_main.width());
		float initial_y = (float) (padding * rectf_main.height() + Math.random() * (1 - 2 * padding) * rectf_main.height());
		
		entries.add(new MovingEntry(initial_x, initial_y, bitmaps));
	}
	
	
	private Activity_Loading_Base getLoadingActivity() {
		return (Activity_Loading_Base) getContext();
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
		
		paint_background.setColor(ConfigurationUtils_Colours.getConfigByID(Application_Base.getInstance().getUserSettings().uiColoursID).getColour_Square_Black());
		if (getBitmapBackground() != null) {
			paint_background.setAlpha(77);
			canvas.drawBitmap(getBitmapBackground(), null, rectf_main, paint_background);
		} else {
			canvas.drawRect(0, 0, rectf_main.width(), rectf_main.height(), paint_background);
		}
		
		//System.out.println("entries=" + entries.size());
		
		for (int iter=MAX_ITERS; iter >= 0; iter--) {
			for (int i=0; i<entries.size(); i++) {
				MovingEntry entry = entries.get(i);
				if (entry.clicks == iter || (entry.clicks > MAX_ITERS && iter == MAX_ITERS)) {
					DrawingUtils.drawInCenter(canvas, paint_images, getSquareSize(),
							entry.coordinates.x, entry.coordinates.y, entry.getBitmap(iter));	
				}
			}
		}


		if (getLoadingActivity().isDone()) {

			buttonarea_start.draw(canvas);

		} else {

			textarea_label_loading.draw(canvas);
		}
		
		updateCoordinates();

		invalidate();
	}
	
	
	private void updateCoordinates() {
		
		
		for (int i=0; i<entries.size(); i++) {
			
			MovingEntry entry = entries.get(i);
			
			if (entry.clicks >= MAX_ITERS) {
				continue;
			}

			float speed_x = entry.speed_x * (1 + entry.clicks);
			float speed_y = entry.speed_y * (1 + entry.clicks);

			entry.coordinates.x += speed_x;
			entry.coordinates.y += speed_y;
			
			if (entry.coordinates.x < 0) {
				entry.coordinates.x = 0;
				entry.speed_x = -entry.speed_x;
			}
			if (entry.coordinates.x > rectf_main.width()) {
				entry.coordinates.x = rectf_main.width();
				entry.speed_x = -entry.speed_x;
			}
			if (entry.coordinates.y < 0) {
				entry.coordinates.y = 0;
				entry.speed_y = -entry.speed_y;
			}
			if (entry.coordinates.y > rectf_main.height()) {
				entry.coordinates.y = rectf_main.height();
				entry.speed_y = -entry.speed_y;
			}
		}
	}


	@Override
	public boolean onTouch(View _view, MotionEvent event) {

		int action = event.getAction();

		System.out.println("View_Loading_Base.onTouch: action=" + action);

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


	private void processEvent_DOWN(MotionEvent event) {

		float x = event.getX();

		float y = event.getY();


		if (isOverStartButton(x, y)) {

			selectButton_Start();

		} else {

			pushed(x, y);
		}
	}


	private void processEvent_MOVE(MotionEvent event) {

		float x = event.getX();

		float y = event.getY();

		if (isOverStartButton(x, y)) {

			selectButton_Start();

			System.out.println("View_Loading_Base.processEvent_MOVE: select start button");
		} else {

			deselectButton_Start();

			System.out.println("View_Loading_Base.processEvent_MOVE: de-select start button");
		}
	}


	private void processEvent_UP(MotionEvent event) {


		float x = event.getX();
		float y = event.getY();


		deselectButton_Start();


		if (isOverStartButton(x, y)) {

			if (getLoadingActivity().isDone()) {

				Intent i = new Intent(getContext(), ((Activity_Loading_Base) getContext()).getNextActivityClass());

				getContext().startActivity(i);
			}
		}
	}
}
