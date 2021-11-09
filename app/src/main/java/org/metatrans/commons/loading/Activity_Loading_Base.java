package org.metatrans.commons.loading;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.metatrans.commons.Activity_Base;
import org.metatrans.commons.Alerts_Base;
import org.metatrans.commons.R;
import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.cfg.colours.IConfigurationColours;
import org.metatrans.commons.engagement.IAchievementsProvider;
import org.metatrans.commons.engagement.ISocialProvider;
import org.metatrans.commons.menu.Activity_Menu_Colours_Base;
import org.metatrans.commons.model.GameData_Base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;


public abstract class Activity_Loading_Base extends Activity_Base {
	
	
	protected static final int VIEW_ID_LOADING = 352462787;
	protected static final int VIEW_ID_INVITE = 12397343;
	
	
	private ExecutorService executor;
	private Handler uiHandler;
	
	protected boolean loaded = false;
	
	private long timestamp_created;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		

		/*
		boolean currentGameCompleted = getGameData().isCountedAsCompleted();
		if (((Application_Base)getApplication()).getEngagementProvider().getLeaderboardsProvider() != null) {
			((Application_Base) getApplication()).getEngagementProvider().getLeaderboardsProvider().setEnabled(currentGameCompleted);
		}*/


		super.onCreate(savedInstanceState);
		
		System.out.println("Activity_Loading_Base:  onCreate");
		
		try {
			
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			
			executor = Executors.newCachedThreadPool();
			uiHandler = new Handler(Looper.getMainLooper());
			
			//initUI();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		timestamp_created = System.currentTimeMillis();
	}
	
	
	protected GameData_Base getGameData() { 
		return ((Application_Base)getApplication()).getGameData();
	}
	
	protected abstract Class<? extends Activity_Base> getNextActivityClass();
	
	protected abstract Class<? extends Activity_Base> getActivityClass_Menu2();
	
	protected abstract View getLoadingView();
	
	protected abstract IConfigurationColours getColoursCfg();
	
	protected abstract void openInterstitial();
	
	
	protected Class<? extends Activity_Base> getActivityClass_Menu1() {
		return Activity_Menu_Colours_Base.class;
	}


	protected int getText_Loading() {
		return R.string.loading;
	}


	protected int getText_Menu0() {
		return R.string.button_start;
	}
	
	protected int getText_Menu1() {
		return R.string.menu_colour_scheme;
	}
	
	
	protected abstract int getText_Menu2();
	
	
	protected int getRateReviewIconID() {
		return R.drawable.ic_vote_thumb_up_gray1;
	}
	
	
	protected int getLayout() {
		return R.layout.commons_loading;
	}
	
	protected int getLayoutID() {
		return R.id.commons_layout_loading;
	}
	
	
	@Override
	public void onStart() {
		
		super.onStart();
		
		if (!getSocialProvider().isConnected()
				&& !getSocialProvider().isSignInRejected()) {
			getSocialProvider().connect();
		}
	}
	
	
	@Override
	public void onStop() {
		
		
		if (uiHandler != null) uiHandler.removeCallbacksAndMessages(null);
		
		super.onStop();
	}
	
	
	@Override
	protected void onDestroy() {
		
		List<Runnable> rejected = executor.shutdownNow();
		System.out.println("Activity_Loading_Base: shuting down executor -> rejected " + rejected.size() + " jobs.");
		
		if (uiHandler != null) uiHandler.removeCallbacksAndMessages(null);
		
		executor = null;
		
		uiHandler = null;
		
		super.onDestroy();
		
		System.gc();
		
		
		if (getSocialProvider().isConnected()) {
			getSocialProvider().disconnect();
		}
	}
	
	
	@Override
	public void onPause() {
		
		System.out.println("Activity_Loading_Base:  onPause");
		
		if (uiHandler != null) uiHandler.removeCallbacksAndMessages(null);
		
		super.onPause();
		
		System.gc();
	}
	
	
	@Override
	protected void onResume() {
		
		System.out.println("Activity_Loading_Base:  onResume");
		
		super.onResume();
		
		try {
			
			initUI();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void dettachTopViews(ViewGroup frame) {
		View old_view_invite_friends = frame.findViewById(VIEW_ID_INVITE);
		if (old_view_invite_friends != null) {
			frame.removeView(old_view_invite_friends);
		}
		getSocialProvider().detachSignInView(frame);
		getSocialProvider().detachLikeView(frame);

		if (Application_Base.getInstance().getEngagementProvider().getLeaderboardsProvider() != null) {
			Application_Base.getInstance().getEngagementProvider().getLeaderboardsProvider().detachLeaderboardView(frame);
		}
	}
	
	
	private void attachTopViews(ViewGroup frame) {
		
		//View_Loading_Base view_loading = (View_Loading_Base) frame.findViewById(VIEW_ID_LOADING);
		//view_loading.createButtons();

		ViewWithLeaderBoard view_loading = frame.findViewById(VIEW_ID_LOADING);
		
		IConfigurationColours coloursCfg = getColoursCfg();
		
		//Add SignIn view
		View view_social = getSocialProvider().getSignInView(coloursCfg);
		if (view_social != null) {
			frame.addView(view_social);
		} else {
			System.out.println("Activity_Loading_Base:  onResume> SignIn view is null");
		}
		
		//Add Like view
		if (false && getSocialProvider().isConnected()) {
			
			/*View view_like = getSocialProvider().getLikeView(coloursCfg, view_loading.getRectangle_GooglePlus());
			if (view_like != null) {
				frame.addView(view_like);
			} else {
				System.out.println("Like view is null");
			}*/
		}
		
		//Add achievements and leaderboard view
		RectF rectf_leaderboards = view_loading.getRectangle_LeaderBoards();

		if (rectf_leaderboards != null && Application_Base.getInstance().getEngagementProvider().getLeaderboardsProvider() != null) {

			View _view_leaderboards = Application_Base.getInstance().getEngagementProvider().getLeaderboardsProvider().getLeaderboardView(coloursCfg, rectf_leaderboards);

			IAchievementsProvider achievements_provider = Application_Base.getInstance().getEngagementProvider().getAchievementsProvider();
			View _view_achievements = null;
			if (achievements_provider != null) {
				_view_achievements = Application_Base.getInstance().getEngagementProvider().getAchievementsProvider().getAchievementsView(coloursCfg, rectf_leaderboards);
			}

			if (_view_leaderboards != null && _view_achievements != null) {
				if (_view_leaderboards != _view_achievements) {
					throw new IllegalStateException("_view_leaderboards != _view_achievements");
				}
			}

			System.out.println("Activity_Loading_Base:  onResume> rectf_leaderboards=" + rectf_leaderboards);

			if (_view_leaderboards != null) {
				frame.addView(_view_leaderboards);
			}
		}
	}
	
	
	private Application_Base getApp() {
		return (Application_Base)getApplication();
	}
	
	
	private ISocialProvider getSocialProvider() {
		return getApp().getEngagementProvider().getSocialProvider();
	}
	
	
	private void initUI() {
		
		System.out.println("Activity_Loading_Base: initUI");
		
		//IConfigurationColours coloursCfg = getColoursCfg();
		
		//if (old_colours_cfg_id == -1 || old_colours_cfg_id != coloursCfg.getID()) {
			
			setContentView(getLayout());
			
			FrameLayout frame = (FrameLayout) findViewById(getLayoutID());
			
			//Remove old view if exists
			View old_view = findViewById(VIEW_ID_LOADING);

			if (old_view != null) {
				frame.removeView(old_view);	
			}
			
			//Add new view
			View view_loading = getLoadingView();

			view_loading.setId(VIEW_ID_LOADING);

			frame.addView(view_loading);
			
			
			//Attach top view
			dettachTopViews(frame);
			attachTopViews(frame);

		getExecutor().execute(new Runnable() {

			@Override
			public void run() {

				try {

					try {

						//Wait images to fully load in the memory before drawing them on the screen
						Thread.sleep(555);

					} catch (InterruptedException e) {}

					ViewWithLeaderBoard view = (ViewWithLeaderBoard) findViewById(VIEW_ID_LOADING);

					view.initPiecesBitmaps();

				} catch(Exception e) {
					e.printStackTrace();
				}
			}

		});


		getExecutor().execute(new Runnable() {

			@Override
			public void run() {

				try {

					if (!loaded) {

						try {

							//Wait images to fully load in the memory before drawing them on the screen
							Thread.sleep(111);

						} catch (InterruptedException e) {}

						load();

						loaded = true;
					}

				} catch(Exception e) {
					e.printStackTrace();
				}
			}

		});
	}


	protected void load() {
		try {
			//Wait images to fully load in the memory before drawing them on the screen
			if (System.currentTimeMillis() - timestamp_created < 1000) {
				Thread.sleep(1000 - (System.currentTimeMillis() - timestamp_created));
			}
		} catch (InterruptedException e) {}
	}

	
	@Override
	public void onBackPressed() {
		
		AlertDialog.Builder adb = Alerts_Base.createAlertDialog_Exit(Activity_Loading_Base.this,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
						openInterstitial();
						
						dialog.dismiss();
						finish();
					}
				});
		
		adb.show();
	}
	
	
	public ExecutorService getExecutor() {
		return executor;
	}
	
	
	public Handler getUiHandler() {
		return uiHandler;
	}
	
	
	public boolean isDone() {
		return loaded;
	}


	public static abstract class ViewWithLeaderBoard extends View {


		public ViewWithLeaderBoard(Context context) {
			super(context);
		}


		public abstract RectF getRectangle_LeaderBoards();

		public abstract void initPiecesBitmaps();
	}
}
