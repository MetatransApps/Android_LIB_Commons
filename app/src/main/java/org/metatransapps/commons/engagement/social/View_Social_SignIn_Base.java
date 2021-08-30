package org.metatransapps.commons.engagement.social;


import org.metatransapps.commons.DeviceUtils;
import org.metatransapps.commons.cfg.colours.IConfigurationColours;
import org.metatransapps.commons.engagement.ISocialProvider;
import org.metatransapps.commons.ui.ButtonAreaClick_Image;
import org.metatransapps.commons.ui.IButtonArea;
import org.metatransapps.commons.ui.TextArea;
import org.metatransapps.commons.ui.Toast_Base;
import org.metatransapps.commons.ui.utils.BitmapUtils;
import org.metatransapps.commons.ui.utils.DrawingUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


public abstract class View_Social_SignIn_Base extends View implements OnTouchListener {
	
	
	private boolean initialized;
	
	private RectF rectf_main;
	private RectF rectangle_button;
	private RectF rectangle_icon;
	private RectF rectangle_text;
	
	private IButtonArea buttonarea_icon;
	private IButtonArea buttonarea_signin;
	private TextArea textarea_welcome;
	
	protected Paint paint_background;
	
	private ISocialProvider provider;
	//private Executor executor;
	//private Handler uiHandler;
	
	private int old_state = -1;
	
	private IConfigurationColours coloursCfg;
	
	private Bitmap signinbutton;
	
	
	public View_Social_SignIn_Base(Context context, ISocialProvider _provider, IConfigurationColours _coloursCfg) {
		
		super(context);
		
		provider = _provider;
		//executor = _executor;
		
		coloursCfg = _coloursCfg;
		
		rectf_main 			= new RectF();
		rectangle_button 	= new RectF();
		rectangle_icon 		= new RectF();
		rectangle_text 		= new RectF();
		
		paint_background 	= new Paint();
		
		signinbutton = BitmapUtils.fromResource(getContext(), getResID_Button_SignIn());
		
		setOnTouchListener(this);
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		if (!initialized) {
			
			int MARGIN = 5;
			
			rectf_main.left = 0;
			rectf_main.right = getMeasuredWidth();
			rectf_main.top = 0;
			rectf_main.bottom = getMeasuredHeight() / 13;
			
			
			rectangle_button.left = MARGIN;
			rectangle_button.right = (1 * (rectf_main.right - rectf_main.left)) / 4;
			rectangle_button.top = rectf_main.top + MARGIN;
			rectangle_button.bottom = rectf_main.bottom - MARGIN;
			
			//Adjust for google branding policy
			float x_image = signinbutton.getWidth();
			float y_image = signinbutton.getHeight();
			float x_area = rectangle_button.width();
			float y_area = rectangle_button.height();
			float x_rate = x_image / x_area;
			float y_rate = y_image / y_area;
			
			if (x_rate > y_rate) {
				x_image /= x_rate;
				y_image /= x_rate;
			} else {
				x_image /= y_rate;
				y_image /= y_rate;				
			}
			
			//rectangle_button.left = rectangle_button.left + ((rectangle_button.right - rectangle_button.left) - x_image) / 2 ;
			rectangle_button.top = rectangle_button.top + ((rectangle_button.bottom - rectangle_button.top) - y_image) / 2 ;
			rectangle_button.right = rectangle_button.left + x_image;
			rectangle_button.bottom = rectangle_button.top + y_image;
			
			
			rectangle_icon.left = rectangle_button.right + MARGIN;
			rectangle_icon.right = rectangle_icon.left + rectf_main.bottom - rectf_main.top - 2 * MARGIN;
			rectangle_icon.top = rectf_main.top + MARGIN;
			rectangle_icon.bottom = rectf_main.bottom - MARGIN;
			
			rectangle_text.left = rectangle_icon.right + MARGIN;
			rectangle_text.right = rectf_main.right - MARGIN;
			rectangle_text.top = rectf_main.top + MARGIN;
			rectangle_text.bottom = rectf_main.bottom - MARGIN;
			
			buttonarea_signin =  new ButtonAreaClick_Image(rectangle_button,
					signinbutton,
					coloursCfg.getColour_Square_Black(),
					coloursCfg.getColour_Square_White()
					);
			
			textarea_welcome = new TextArea(rectangle_text,
					"  " + "No connection with the server" + "  ",
					coloursCfg.getColour_Square_Black(),
					coloursCfg.getColour_Square_ValidSelection()
					);
			
			initialized = true;
		}
	}
	
	
	@Override
	protected synchronized void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
		
		paint_background.setColor(coloursCfg.getColour_Delimiter());
		DrawingUtils.drawRoundRectangle(canvas, paint_background, rectf_main);
		
		
		if (old_state != provider.getState()) {
			if (provider.getState() == ISocialProvider.STATE_DEFAULT) {
				buttonarea_signin =  new ButtonAreaClick_Image(rectangle_button,
						BitmapUtils.fromResource(getContext(), getResID_Button_SignIn()),
						coloursCfg.getColour_Square_Black(),
						coloursCfg.getColour_Square_ValidSelection()
						);
			} else if (provider.getState() == ISocialProvider.STATE_IN_PROGRESS) {
				buttonarea_signin =  new ButtonAreaClick_Image(rectangle_button,
						BitmapUtils.fromResource(getContext(), getResID_Button_InProgress()),
						coloursCfg.getColour_Square_Black(),
						coloursCfg.getColour_Square_ValidSelection()
						);
			} else if (provider.getState() == ISocialProvider.STATE_SIGNED_IN) {
				buttonarea_signin =  new ButtonAreaClick_Image(rectangle_button,
						BitmapUtils.fromResource(getContext(), getResID_Button_SignOut()),
						coloursCfg.getColour_Square_Black(),
						coloursCfg.getColour_Square_ValidSelection()
						);
			} else if (provider.getState() == ISocialProvider.STATE_ERROR) {
				buttonarea_signin =  new ButtonAreaClick_Image(rectangle_button,
						BitmapUtils.fromResource(getContext(), getResID_Button_SignIn()),
						coloursCfg.getColour_Square_Black(),
						coloursCfg.getColour_Square_ValidSelection()
						);
			} else {
				throw new IllegalStateException();
			}

		}
		//paint_background.setColor(Color.WHITE);
		//DrawingUtils.drawRoundRectangle(canvas, paint_background, rectangle_button);
		buttonarea_signin.draw(canvas);
		
		
		if (old_state != provider.getState()) {
			if (provider.isConnected() || provider.isConnecting()) {
				textarea_welcome = new TextArea(rectangle_text,
						"  " + provider.getStateMesage() + "  ",
						coloursCfg.getColour_Square_Black(),
						coloursCfg.getColour_Square_ValidSelection()
						);
			} else {
				textarea_welcome = new TextArea(rectangle_text,
						"  " + provider.getStateMesage() + "  ",
						coloursCfg.getColour_Square_Black(),
						coloursCfg.getColour_Square_InvalidSelection()
						);
			}
		}
		//paint_background.setColor(Color.BLACK);
		//DrawingUtils.drawRoundRectangle(canvas, paint_background, rectangle_text);
		textarea_welcome.draw(canvas);
		
		if (provider.getUserIcon() != null) {
			
			if (buttonarea_icon == null) {
				buttonarea_icon =  new ButtonAreaClick_Image(rectangle_icon,
					provider.getUserIcon(),
					coloursCfg.getColour_Square_Black(),
					coloursCfg.getColour_Square_White()
					);
			}
			
			buttonarea_icon.draw(canvas);
		} else {
			buttonarea_icon = null;
		}
		
		
		old_state = provider.getState();
	}
	
	
	public abstract int getResID_Button_SignIn();
	
	public abstract int getResID_Button_SignOut();
	
	public abstract int getResID_Button_InProgress();
	
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		
		float x = event.getX();
		float y = event.getY();
		
		if (!rectf_main.contains(x, y)) {
			return false;
		}
		
		
		if (provider.isConnecting()
				|| provider.getState() == ISocialProvider.STATE_IN_PROGRESS) {
			return true;
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
	
	
	private boolean isOverButton_SignIn(float x, float y) {
		return rectangle_button.contains(x, y);
	}
	
	
	private void processEvent_DOWN(MotionEvent event) {
		
		float x = event.getX();
		float y = event.getY();
		
		if (isOverButton_SignIn(x, y)) {
			
			buttonarea_signin.select();

		}
	}
	
	
	private void processEvent_MOVE(MotionEvent event) {
		
		float x = event.getX();
		float y = event.getY();
		
		if (isOverButton_SignIn(x, y)) {
			
			buttonarea_signin.select();

		} else {
			
			buttonarea_signin.deselect();
		}
	}
	
	
	private void processEvent_UP(MotionEvent event) {
		
		float x = event.getX();
		float y = event.getY();
		
		if (isOverButton_SignIn(x, y)) {
			
			buttonarea_signin.deselect();
			
			if (provider.getState() == ISocialProvider.STATE_SIGNED_IN) {
				
				if (provider.isConnected()) {
					provider.disconnectAndClear();
				} else {
					provider.setState(ISocialProvider.STATE_DEFAULT);
				}
				
				provider.setSignInRejected(true);
				
			} else if (provider.getState() == ISocialProvider.STATE_DEFAULT) {
				
				if (DeviceUtils.isConnectedOrConnecting()) {
					provider.connect();
				} else {
					Toast_Base.showToast_InCenter_Short(getContext(), "  " + getContext().getString(org.metatransapps.commons.R.string.label_noconnection) + "  ");
				}
				
			} else if (provider.getState() == ISocialProvider.STATE_ERROR) {
				
				if (DeviceUtils.isConnectedOrConnecting()) {
					provider.connect();
				} else {
					Toast_Base.showToast_InCenter_Short(getContext(), "  " + getContext().getString(org.metatransapps.commons.R.string.label_noconnection) + "  ");
				}
				
			} else {
				//provider.connect();
			}
		} else {
			buttonarea_signin.deselect();
		}
	}
}
