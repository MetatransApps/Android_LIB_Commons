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
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;


public abstract class View_Loading_Base extends View {
	
	int MAX_ITERS = 4;
	int STEP = 10;
	
	private List<MovingEntry> entries;

	
	private RectF rectf_main;
	private RectF rectf_buttons;
	private RectF rectf_button_start;
	private RectF rectf_button_invite;
	private RectF rectf_button_moregames;
	private RectF rectf_leaderboards;
	private RectF rectf_button_menu2;
	private RectF rectf_button_menu1;
	
	private RectF rectf_button_googleplus;
	private RectF rectf_button_rate_review;
	
	private TextArea textarea_label_loading;
	private IButtonArea buttonarea_start;
	private IButtonArea buttonarea_moregames;
	private IButtonArea buttonarea_levels;
	private IButtonArea buttonarea_colours;
	private IButtonArea buttonarea_rate_review;
	
	protected Paint paint_background;
	protected Paint paint_images;
	
	private Runnable refresher; 
	
	private boolean sent_event_one_stoped = false;
	private boolean sent_event_all_stoped = false;
	
	
	public View_Loading_Base(Context context) {
		
		super(context);
		
		rectf_main 					= new RectF();
		rectf_buttons				= new RectF();
		rectf_button_start 			= new RectF();
		rectf_button_moregames		= new RectF();
		rectf_button_invite 		= new RectF();
		
		rectf_leaderboards 			= new RectF();
		
		rectf_button_menu2			= new RectF();
		rectf_button_menu1			= new RectF();
		
		rectf_button_googleplus		= new RectF();
		rectf_button_rate_review	= new RectF();
		
		paint_background 			= new Paint();
		paint_images 				= new Paint();
		
		entries 					= new Vector<MovingEntry>();
		
		refresher 					= new Update(this);
	}
	
	
	protected abstract void initPiecesBitmaps();
	protected abstract Bitmap[] getCommonBitmaps();
	protected abstract Bitmap getBitmapBackground();//Can be null
	
	
	public RectF getRectangle_LeaderBoards() {
		return rectf_leaderboards;
	}
	
	
	public RectF getRectangle_InviteFriends() {
		return rectf_button_invite;
	}
	
	
	public RectF getRectangle_GooglePlus() {
		return rectf_button_googleplus;
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
		
		//if (!initialized) {
			
			rectf_main.left = 0;
			rectf_main.right = getMeasuredWidth();
			rectf_main.top = 0;
			rectf_main.bottom = getMeasuredHeight();
			
			int MARGIN = 10;
			int width = getMeasuredWidth() / 3;
			int height =  getMeasuredHeight() / 17;
			
			rectf_buttons.left = getMeasuredWidth() / 2 - width / 2 - MARGIN;
			rectf_buttons.right = getMeasuredWidth() / 2 + width / 2 + MARGIN;
			rectf_buttons.top = getMeasuredHeight() / 2 - height / 2 - 2 * height - 3 * MARGIN;
			rectf_buttons.bottom = getMeasuredHeight() / 2 + height / 2 + 2 * height + 3 * MARGIN;
			
			rectf_button_start.left = getMeasuredWidth() / 2 - width / 2;
			rectf_button_start.right = getMeasuredWidth() / 2 + width / 2;
			rectf_button_start.top = getMeasuredHeight() / 2 - height / 2;
			rectf_button_start.bottom = getMeasuredHeight() / 2 + height / 2;
			
			rectf_button_menu2.left = rectf_button_start.left;
			rectf_button_menu2.top = rectf_button_start.top - height - MARGIN;
			rectf_button_menu2.right = rectf_button_start.right;
			rectf_button_menu2.bottom = rectf_button_menu2.top + height;
			
			rectf_button_invite.left = rectf_button_menu2.left;
			rectf_button_invite.top = rectf_button_menu2.top - height - MARGIN;
			rectf_button_invite.right = rectf_button_menu2.right;
			rectf_button_invite.bottom = rectf_button_invite.top + height;
			
			rectf_button_moregames.left = rectf_button_start.left;
			rectf_button_moregames.top = rectf_button_start.bottom + MARGIN;
			rectf_button_moregames.right = rectf_button_start.right;
			rectf_button_moregames.bottom = rectf_button_moregames.top + height;
			
			rectf_button_menu1.left = rectf_button_moregames.left;
			rectf_button_menu1.top = rectf_button_moregames.bottom + MARGIN;
			rectf_button_menu1.right = rectf_button_moregames.right;
			rectf_button_menu1.bottom = rectf_button_menu1.top + height;
			
			
			float button_width = rectf_button_moregames.right - rectf_button_moregames.left;
			
			if (getMeasuredHeight() > getMeasuredWidth()) {
				
				rectf_leaderboards.left = getMeasuredWidth() / 2 - 1 * button_width / 2 - MARGIN;
				rectf_leaderboards.right = getMeasuredWidth() / 2 + 1 * button_width / 2 + MARGIN;
				rectf_leaderboards.bottom = rectf_buttons.top - MARGIN;//button_height / 2;
				rectf_leaderboards.top = rectf_leaderboards.bottom - 1 * (rectf_leaderboards.right - rectf_leaderboards.left) / 3;
				
			} else {
				
				rectf_leaderboards.left = getMeasuredWidth() / 2 - 1 * button_width / 3;
				rectf_leaderboards.right = getMeasuredWidth() / 2 + 1 * button_width / 3;
				rectf_leaderboards.bottom = rectf_buttons.top - MARGIN;//button_height / 2;
				rectf_leaderboards.top = rectf_leaderboards.bottom - 1 * (rectf_leaderboards.right - rectf_leaderboards.left) / 3;
			}
			
			
			float googleplus_button_half_size = rectf_buttons.left / 4;
			
			//rectf_button_googleplus.left = MARGIN;
			//rectf_button_googleplus.top = rectf_button_start.top;
			//rectf_button_googleplus.right = rectf_buttons.left - MARGIN;
			//rectf_button_googleplus.bottom = rectf_button_start.bottom;
			rectf_button_googleplus.left = rectf_buttons.left / 2 - googleplus_button_half_size;
			rectf_button_googleplus.top = rectf_button_start.top;//rectf_button_start.top + (rectf_button_start.bottom - rectf_button_start.top) / 2 - googleplus_button_half_size;
			rectf_button_googleplus.right = rectf_buttons.left / 2 + googleplus_button_half_size;
			rectf_button_googleplus.bottom = rectf_button_start.bottom;//rectf_button_start.top + (rectf_button_start.bottom - rectf_button_start.top) / 2 + googleplus_button_half_size;
			
			rectf_button_rate_review.left = rectf_buttons.right + (rectf_main.right - rectf_buttons.right) / 2 - googleplus_button_half_size;
			rectf_button_rate_review.top = rectf_button_start.top + (rectf_button_start.bottom - rectf_button_start.top) / 2 - googleplus_button_half_size;
			rectf_button_rate_review.right = rectf_buttons.right + (rectf_main.right - rectf_buttons.right) / 2 + googleplus_button_half_size;
			rectf_button_rate_review.bottom = rectf_button_start.top + (rectf_button_start.bottom - rectf_button_start.top) / 2 + googleplus_button_half_size;
			
			createButtons();
			
			this.setMeasuredDimension( (int) (rectf_main.right - rectf_main.left), (int) (rectf_main.bottom - rectf_main.top) );
			
			
			//initialized = true;
		//}
	}
	
	
	private void createButtons() {
		
		//if (!initialized) {
		//	return;
		//}
		
		buttonarea_rate_review = new ButtonAreaClick_Image(rectf_button_rate_review, BitmapUtils.fromResource(getContext(), getLoadingActivity().getRateReviewIconID()),
				getLoadingActivity().getColoursCfg().getColour_Square_ValidSelection(),
				getLoadingActivity().getColoursCfg().getColour_Square_MarkingSelection(),
				true);
		
		
		textarea_label_loading = new TextArea(rectf_button_start, false, " " + getLoadingActivity().getString(R.string.loading) + " ",
				getLoadingActivity().getColoursCfg().getColour_Square_Black(),
				getLoadingActivity().getColoursCfg().getColour_Square_ValidSelection());
		
		//Color.rgb(51, 51, 51) delimeter
		//Color.rgb(153, 153, 153) white
		//Color.rgb(102, 102, 102) black
		buttonarea_start = new ButtonAreaClick(rectf_button_start, "  " + getLoadingActivity().getString(getLoadingActivity().getText_Menu0()) + "  ",
				getLoadingActivity().getColoursCfg().getColour_Square_ValidSelection(),
				getLoadingActivity().getColoursCfg().getColour_Square_Black(),
				getLoadingActivity().getColoursCfg().getColour_Square_MarkingSelection()
				//getLoadingActivity().getColoursCfg().getColour_Delimiter(),
				//getLoadingActivity().getColoursCfg().getColour_Square_ValidSelection(),
				//getLoadingActivity().getColoursCfg().getColour_Square_Black()
				);
		
		buttonarea_moregames =  new ButtonAreaClick(rectf_button_moregames, " " + getContext().getString(R.string.label_moregames) + " ",
				//coloursCfg.getColour_Delimiter(), coloursCfg.getColour_Square_White(), coloursCfg.getColour_Square_ValidSelection());
				getLoadingActivity().getColoursCfg().getColour_Square_ValidSelection(),
				getLoadingActivity().getColoursCfg().getColour_Square_Black(),
				getLoadingActivity().getColoursCfg().getColour_Square_MarkingSelection());
		
		buttonarea_levels =  new ButtonAreaClick(rectf_button_menu2, "  " + getContext().getString(getLoadingActivity().getText_Menu2()) + "  ",
				//coloursCfg.getColour_Delimiter(), coloursCfg.getColour_Square_White(), coloursCfg.getColour_Square_ValidSelection());
				getLoadingActivity().getColoursCfg().getColour_Square_ValidSelection(),
				getLoadingActivity().getColoursCfg().getColour_Square_Black(),
				getLoadingActivity().getColoursCfg().getColour_Square_MarkingSelection());
		
		buttonarea_colours =  new ButtonAreaClick(rectf_button_menu1, "  " + getContext().getString(getLoadingActivity().getText_Menu1()) + "  ",
				//coloursCfg.getColour_Delimiter(), coloursCfg.getColour_Square_White(), coloursCfg.getColour_Square_ValidSelection());
				getLoadingActivity().getColoursCfg().getColour_Square_ValidSelection(),
				getLoadingActivity().getColoursCfg().getColour_Square_Black(),
				getLoadingActivity().getColoursCfg().getColour_Square_MarkingSelection());
	}
	
	
	public float getSquareSize() {
		return Math.min(rectf_main.width(), rectf_main.height()) / 5;
	}
	
	
	public boolean isOverRateButton(float x, float y) {
		return rectf_button_rate_review.contains(x, y);
	}
	
	
	public boolean isOverStartButton(float x, float y) {
		return rectf_button_start.contains(x, y) && getLoadingActivity().isDone();
	}
	
	
	public boolean isOverMoreGamesButton(float x, float y) {
		return rectf_button_moregames.contains(x, y);
	}
	
	
	public boolean isOverSelectColorButton(float x, float y) {
		return rectf_button_menu1.contains(x, y);
	}
	
	
	public boolean isOverSelectLevelButton(float x, float y) {
		return rectf_button_menu2.contains(x, y);
	}
	
	
	public void selectButton_Start() {
		
		buttonarea_start.select();
		invalidate();
	}
	
	public void deselectButton_Start() {
		
		buttonarea_start.deselect();
		invalidate();
	}
	
	
	public void selectButton_MoreGames() {
		
		buttonarea_moregames.select();
		invalidate();
	
	}

	
	public void deselectButton_MoreGames() {
		
		buttonarea_moregames.deselect();
		invalidate();
	}
	
	
	public void selectButton_Rate() {
		
		buttonarea_rate_review.select();
		invalidate();
	
	}

	
	public void deselectButton_Rate() {
		
		buttonarea_rate_review.deselect();
		invalidate();
	}
	
	
	public void selectButton_SelectColor() {
		
		buttonarea_colours.select();
		invalidate();
	}
	
	
	public void deselectButton_SelectColor() {
		
		buttonarea_colours.deselect();
		invalidate();
	}
	
	
	public void selectButton_SelectLevel() {
		
		buttonarea_levels.select();
		invalidate();
	}
	
	
	public void deselectButton_SelectLevel() {
		
		buttonarea_levels.deselect();
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
		
		paint_background.setColor(getLoadingActivity().getColoursCfg().getColour_Delimiter());
		DrawingUtils.drawRoundRectangle(canvas, paint_background, rectf_buttons);		
		
		if (getLoadingActivity().isDone()) {
			buttonarea_start.draw(canvas);	
		} else {
			textarea_label_loading.draw(canvas);
		}
		
		buttonarea_moregames.draw(canvas);
		
		buttonarea_levels.draw(canvas);
		buttonarea_colours.draw(canvas);
		
		
		//DrawingUtils.drawRoundRectangle(canvas, paint_background, getRectangle_GooglePlus());
		//DrawingUtils.drawRoundRectangle(canvas, paint_background, rectf_button_rate_review);
		buttonarea_rate_review.draw(canvas);
		
		updateCoordinates();
		
		getLoadingActivity().getUiHandler().post(refresher);
	}
	
	
	private void updateCoordinates() {
		
		
		for (int i=0; i<entries.size(); i++) {
			
			MovingEntry entry = entries.get(i);
			
			if (entry.clicks >= MAX_ITERS) {
				continue;
			}
			
			entry.coordinates.x += (float) ((Math.random() - 0.5) * (STEP + 4 * entry.clicks * STEP));
			entry.coordinates.y += (float) ((Math.random() - 0.5) * (STEP + 4 * entry.clicks * STEP));
			
			if (entry.coordinates.x < 0) {
				entry.coordinates.x = 0;
			}
			if (entry.coordinates.x > rectf_main.width()) {
				entry.coordinates.x = rectf_main.width();
			}
			if (entry.coordinates.y < 0) {
				entry.coordinates.y = 0;
			}
			if (entry.coordinates.y > rectf_main.height()) {
				entry.coordinates.y = rectf_main.height();
			}
		}
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
