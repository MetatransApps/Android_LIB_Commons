package org.metatrans.commons.engagement.achievements;


import org.metatrans.commons.Activity_Base;
import org.metatrans.commons.R;
import org.metatrans.commons.achievements.IAchievementsManager;
import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.events.api.IEvent_Base;
import org.metatrans.commons.events.api.IEventsManager;
import org.metatrans.commons.web.Activity_WebView_StatePreservingImpl_With_VideoPlayer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class Activity_Scores extends Activity_Base {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.scores_view);		
		
		try {
			
			IAchievementsManager achievementsManager = ((Application_Base)getApplication()).getAchievementsManager();
			
			final TextView titleView = (TextView) findViewById(R.id.scores_view_title);
			
			int scores = achievementsManager.getScores(this);
			
			if (scores < achievementsManager.getMaxScores(this)) {
				
				setBackgroundPoster(R.id.scores_view_layout, 77);
				
				TextView text1View = (TextView) findViewById(R.id.scores_view_text1);
				float textSize = text1View.getTextSize();
				text1View.setTextSize(2 * textSize);
				//text1View.setText("" + AchievementsUtils.getScores(this) + " scores");
				text1View.setText(R.string.youhave);
				
				titleView.setTextColor(Color.GREEN);
				titleView.setTextSize(2 * textSize);
				titleView.setText("" + scores);
				
				TextView text2View = (TextView) findViewById(R.id.scores_view_text2);
				text2View.setTextSize(2 * textSize);
				//text2View.setText("You will be able to use the scores \r\n for disabling the Ads in one of \r\n the next application versions. Stay tuned.");
				text2View.setText(R.string.scores);
				//text2View.setText("Scores " + "(<a href=\"file:///android_asset/www/help.html#scores\">?</a>)");
			} else {
				
				setBackgroundPoster(R.id.scores_view_layout, 255);
				
				TextView text1View = (TextView) findViewById(R.id.scores_view_text1);
				text1View.setTextColor(Color.GREEN);
				float textSize = text1View.getTextSize();
				text1View.setTextSize(2 * textSize);
				//text1View.setText("" + AchievementsUtils.getScores(this) + " scores");
				text1View.setText(R.string.achievements_alert_title);
				
				titleView.setTextColor(Color.WHITE);
				titleView.setTextSize(2 * textSize);
				titleView.setText(scores + " " + getString(R.string.scores));
				
				TextView text2View = (TextView) findViewById(R.id.scores_view_text2);
				text2View.setTextColor(Color.GREEN);
				text2View.setTextSize(2 * textSize);
				//text2View.setText("You will be able to use the scores \r\n for disabling the Ads in one of \r\n the next application versions. Stay tuned.");
				text2View.setText(R.string.achievements_alert_title);
				//text2View.setText("Scores " + "(<a href=\"file:///android_asset/www/help.html#scores\">?</a>)");
			}
		    //tv.setText()); + Html.fromHtml(
			//text2View.setMovementMethod(LinkMovementMethod.getInstance());
			//text2View.setAutoLinkMask(mask)
			
			//WebView webView = (WebView) findViewById(R.id.scores_view_webview);
			//webView.setVerticalScrollBarEnabled(true);
			//webView.loadUrl("file:///android_asset/www/help.html#scores");
			
			//android:drawableLeft="@drawable/up_count_big"

			/*
			titleView.setOnTouchListener(
					
					new View.OnTouchListener() {
				
						@Override
						public boolean onTouch(View v, MotionEvent event) {
							
							System.out.println("Activity_Scores: click on scores");
							
							Drawable icon = null;
							
						    final Drawable[] drawables = titleView.getCompoundDrawables();
						    if (drawables != null && drawables.length == 4) {
						    	icon = drawables[2];
							}
						    
						    if (icon != null) {
						    	System.out.println("Activity_Scores: right icon found");
						        if (event.getAction() == MotionEvent.ACTION_DOWN) {
						        	
						        	int fuzz = 10;
						        	
						            final int x = (int) event.getX();
						            final int y = (int) event.getY();
						            final Rect bounds = icon.getBounds();
						            if (x >= (v.getRight() - bounds.width() - fuzz) && x <= (v.getRight() - v.getPaddingRight() + fuzz)
						                    && y >= (v.getPaddingTop() - fuzz) && y <= (v.getHeight() - v.getPaddingBottom()) + fuzz) {
						            	
						            	doJob();
						            	
						                return true;
						            }
						        }
						    }
					        
					        return false;
						}
						
						
						private void doJob() {
							
			            	System.out.println("Activity_Scores: right icon clicked");
							
			            	Activity currentActivity = Application_Base.getInstance().getCurrentActivity();
			            	
							if (currentActivity != null) {
					           	Intent intent = new Intent(currentActivity, Activity_WebView_StatePreservingImpl_With_VideoPlayer.class);
				            	intent.putExtra("URL", "file:///android_asset/www/games.html#" + Application_Base.getInstance().getAppConfig().getTag_ScoresTable());
				            	intent.putExtra("titleID", R.string.scores);
				            	currentActivity.startActivity(intent);
								
			                	IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
			            		eventsManager.register(Activity_Scores.this,
			            				eventsManager.create(IEvent_Base.MENU_OPERATION, IEvent_Base.MENU_OPERATION_OPEN_HELP_SCORES,
			            				"MENU_OPERATION", "OPEN_HELP_SCORES"));
							}
						}
					}
			);
			*/

		} catch(Exception e) {
			e.printStackTrace();
		}
	 	
		//Thanks! improving your idea a bit, I can use Locale.getLanguage().toLowerCase() to get that prefix so I do not need to add prefix var to string.xml
		//Note that getLanguage().substring(0, 2) is not a versitle method in android because zh-rTW (taiwanese a.k.a traditional chinese. zh is simplified chinese) cannot be handled
		
	}

	
	@Override
	public void onResume() {
		
		System.out.println("Activity_Scores: onResume()");
		
		super.onResume();
	}
	
	
	@Override
	protected int getBackgroundImageID() {
		//boolean left_handed = (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
		//return left_handed ? R.drawable.ic_scores : R.drawable.ic_scores;
		return R.drawable.ic_scores;
	}
}
