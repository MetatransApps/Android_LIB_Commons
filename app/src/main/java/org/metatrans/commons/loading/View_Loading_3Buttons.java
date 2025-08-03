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
import org.metatrans.commons.ui.IButtonArea;
import org.metatrans.commons.ui.TextArea;
import org.metatrans.commons.ui.utils.DrawingUtils;
import org.metatrans.commons.ui.utils.ScreenUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;


public abstract class View_Loading_3Buttons extends Activity_Loading_Base.ViewWithLeaderBoard implements View.OnTouchListener {
	
	int MAX_ITERS = 4;

	int STEP = 10;
	
	private List<MovingEntry> entries;

	
	private RectF rectf_main;
	private RectF rectf_button_start;
	private RectF rectf_button_menu2;
	private RectF rectf_button_menu1;
	
	private TextArea textarea_label_loading;
	private IButtonArea buttonarea_start;
	private IButtonArea buttonarea_menu2;
	private IButtonArea buttonarea_menu1;
	
	protected Paint paint_background;
	protected Paint paint_images;
	
	private Runnable refresher; 
	
	private boolean sent_event_one_stoped = false;
	private boolean sent_event_all_stoped = false;
	
	
	public View_Loading_3Buttons(Context context) {

		super(context);
		
		rectf_main 					= new RectF();
		rectf_button_start 			= new RectF();
		
		rectf_button_menu2			= new RectF();
		rectf_button_menu1			= new RectF();
		
		paint_background 			= new Paint();
		paint_images 				= new Paint();
		
		entries 					= new Vector<MovingEntry>();
		
		refresher 					= new Update(this);
	}
	
	
	public abstract void initPiecesBitmaps();


	protected abstract Bitmap[] getCommonBitmaps();


	protected abstract Bitmap getBitmapBackground();//Can be null


	public RectF getRectangle_LeaderBoards() {
		return null;
	}
	
	
	public RectF getRectangle_InviteFriends() {
		return null;
	}
	
	
	public RectF getRectangle_GooglePlus() {
		return null;
	}
	
	
	//@Override
	protected void registerEvent_AllStopped() {
		IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
		eventsManager.register(((Activity)getContext()), IEvent_Base.EVENT_LOADING_ALL_PIECES_STOPPED);
	}
	
	
	//@Override
	protected void registerEvent_1Stopped() {
		IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
		eventsManager.register(((Activity)getContext()), IEvent_Base.EVENT_LOADING_1_PIECE_STOPPED);
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int[] screen_size = ScreenUtils.getScreenSize(getContext());
		int main_width = screen_size[0]; //getMeasuredWidth();
		int main_height = screen_size[1]; //getMeasuredHeight();

		rectf_main.left = 0;
		rectf_main.right = main_width;
		rectf_main.top = 0;
		rectf_main.bottom = main_height;

		int MARGIN = 10;
		int buttons_width = main_width / 2;
		int buttons_height =  main_height / 11;
		int buttons_distance =  (main_height - 6 * buttons_height) / 7;

		if (main_width > main_height) {
			buttons_height *= 1.5;
			buttons_distance =  (main_height - 6 * buttons_height) / 7;
		}

		rectf_button_start.left = (rectf_main.right - rectf_main.left) / 2 - buttons_width / 2;
		rectf_button_start.right = (rectf_main.right - rectf_main.left) / 2 + buttons_width / 2;
		rectf_button_start.top = (rectf_main.bottom - rectf_main.top) / 2 - buttons_height / 2;
		rectf_button_start.bottom = (rectf_main.bottom - rectf_main.top) / 2 + buttons_height / 2;

		rectf_button_menu2.left = rectf_button_start.left;
		rectf_button_menu2.right = rectf_button_start.right;
		rectf_button_menu2.top = (rectf_main.bottom - rectf_main.top) / 2 - (rectf_main.bottom - rectf_main.top) / 4 - buttons_height / 2;
		rectf_button_menu2.bottom = rectf_button_menu2.top + buttons_height;

		rectf_button_menu1.left = rectf_button_start.left;
		rectf_button_menu1.right = rectf_button_start.right;
		rectf_button_menu1.top = (rectf_main.bottom - rectf_main.top) / 2 + (rectf_main.bottom - rectf_main.top) / 4 - buttons_height / 2;
		rectf_button_menu1.bottom = rectf_button_menu1.top + buttons_height;


		createButtons();


		this.setMeasuredDimension( (int) (rectf_main.right - rectf_main.left), (int) (rectf_main.bottom - rectf_main.top) );

			
		setOnTouchListener(this);


		initPiecesBitmaps();
	}
	
	
	private void createButtons() {
		
		
		textarea_label_loading = new TextArea(rectf_button_start, false, " " + getLoadingActivity().getString(getLoadingActivity().getText_Loading()) + " ",
				getLoadingActivity().getColoursCfg().getColour_Square_White(),
				Color.WHITE //getLoadingActivity().getColoursCfg().getColour_Square_ValidSelection()
		);

		/*buttonarea_start = new ButtonAreaClick_Image(rectf_button_start,
				BitmapUtils.fromResource(getContext(), R.drawable.ic_action_playback_play_white),
				getLoadingActivity().getColoursCfg().getColour_Square_ValidSelection(),
				getLoadingActivity().getColoursCfg().getColour_Square_MarkingSelection(),
				false
		);*/

		buttonarea_start = new ButtonAreaClick(rectf_button_start, "  " + getLoadingActivity().getString(getLoadingActivity().getText_Menu0()) + "  ",
				getLoadingActivity().getColoursCfg().getColour_Square_ValidSelection(),
				Color.WHITE, //getLoadingActivity().getColoursCfg().getColour_Square_Black(),
				getLoadingActivity().getColoursCfg().getColour_Square_MarkingSelection());

		buttonarea_menu2 =  new ButtonAreaClick(rectf_button_menu2, "  " + getContext().getString(getLoadingActivity().getText_Menu2()) + "  ",
				//coloursCfg.getColour_Delimiter(), coloursCfg.getColour_Square_White(), coloursCfg.getColour_Square_ValidSelection());
				getLoadingActivity().getColoursCfg().getColour_Square_ValidSelection(),
				Color.WHITE, //getLoadingActivity().getColoursCfg().getColour_Square_Black(),
				getLoadingActivity().getColoursCfg().getColour_Square_MarkingSelection());
		
		buttonarea_menu1 =  new ButtonAreaClick(rectf_button_menu1, "  " + getContext().getString(getLoadingActivity().getText_Menu1()) + "  ",
				//coloursCfg.getColour_Delimiter(), coloursCfg.getColour_Square_White(), coloursCfg.getColour_Square_ValidSelection());
				getLoadingActivity().getColoursCfg().getColour_Square_ValidSelection(),
				Color.WHITE, //getLoadingActivity().getColoursCfg().getColour_Square_Black(),
				getLoadingActivity().getColoursCfg().getColour_Square_MarkingSelection());
	}
	
	
	public float getSquareSize() {
		return Math.min(rectf_main.width(), rectf_main.height()) / 5;
	}
	
	
	public boolean isOverMenu1Button(float x, float y) {
		return rectf_button_menu1.contains(x, y);
	}
	
	
	public boolean isOverMenu2Button(float x, float y) {
		return rectf_button_menu2.contains(x, y);
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
	
	
	public void selectButton_Menu1() {
		
		buttonarea_menu1.select();
		invalidate();
	}
	
	
	public void deselectButton_Menu1() {
		
		buttonarea_menu1.deselect();
		invalidate();
	}
	
	
	public void selectButton_Menu2() {
		
		buttonarea_menu2.select();
		invalidate();
	}
	
	
	public void deselectButton_Menu2() {
		
		buttonarea_menu2.deselect();
		invalidate();
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
	protected synchronized void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
		
		paint_background.setColor(ConfigurationUtils_Colours.getConfigByID(Application_Base.getInstance().getUserSettings().uiColoursID).getColour_Background());

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

		buttonarea_menu2.draw(canvas);
		buttonarea_menu1.draw(canvas);
		
		updateCoordinates();
		
		getLoadingActivity().getUiHandler().post(refresher);

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
	public boolean onTouch(View v, MotionEvent event) {

		int action = event.getAction();

		if (action == MotionEvent.ACTION_DOWN) {

			float x = event.getX();
			float y = event.getY();

			if (isOverStartButton(x, y)) {

				selectButton_Start();

			} else if (isOverMenu1Button(x, y)) {

				selectButton_Menu1();

			} else if (isOverMenu2Button(x, y)) {

				selectButton_Menu2();

			} else {

				pushed(x, y);
			}


		} else if (action == MotionEvent.ACTION_MOVE) {

			float x = event.getX();
			float y = event.getY();

			if (isOverStartButton(x, y)) {

				selectButton_Start();

			} else {

				deselectButton_Start();
			}

			if (isOverMenu1Button(x, y)) {

				selectButton_Menu1();

			} else {

				deselectButton_Menu1();
			}

			if (isOverMenu2Button(x, y)) {

				selectButton_Menu2();

			} else {

				deselectButton_Menu2();
			}

		} else if (action == MotionEvent.ACTION_UP) {

			float x = event.getX();
			float y = event.getY();

			deselectButton_Start();
			deselectButton_Menu1();
			deselectButton_Menu2();

			if (isOverStartButton(x, y)) {

				if (getLoadingActivity().isDone()) {

					Application_Base.getInstance().getSFXManager().playSound(R.raw.sfx_button_pressed_1);

					Intent main = new Intent(getContext(), ((Activity_Loading_Base) getContext()).getNextActivityClass());

					getContext().startActivity(main);
				}
			}

			if (isOverMenu1Button(x, y)) {

				Application_Base.getInstance().getSFXManager().playSound(R.raw.sfx_button_pressed_1);

				Intent menu1 = new Intent(getContext(), ((Activity_Loading_Base) getContext()).getActivityClass_Menu1());

				getContext().startActivity(menu1);
			}

			if (isOverMenu2Button(x, y)) {

				Application_Base.getInstance().getSFXManager().playSound(R.raw.sfx_button_pressed_1);

				Intent menu2 = new Intent(getContext(), ((Activity_Loading_Base) getContext()).getActivityClass_Menu2());

				getContext().startActivity(menu2);
			}
		}


		invalidate();


		return true;
	}


	private static class Update implements Runnable {
		
		
		private View view;
		
		
		public Update(View _view) {
			view = _view;
		}
		
		
		@Override
		public void run() {
			
			try {
				Thread.sleep(35);
			} catch (InterruptedException e) {}
			
			view.invalidate();
		}
	}
}
