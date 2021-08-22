package com.apps.mobile.android.commons.engagement.achievements;


import java.util.List;

import com.apps.mobile.android.commons.Activity_Base;
import com.apps.mobile.android.commons.R;
import com.apps.mobile.android.commons.achievements.IAchievementsManager;
import com.apps.mobile.android.commons.app.Application_Base;
import com.apps.mobile.android.commons.cfg.achievements.IConfigurationAchievements;
import com.apps.mobile.android.commons.ui.list.ListViewFactory;
import com.apps.mobile.android.commons.ui.list.RowItem_IdTD;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;


public class Activity_Achievements_Base extends Activity_Base {
	
	
	@Override
	public synchronized void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
	}
	
	
	@Override
	public void onResume() {
		
		super.onResume();
		
		System.out.println("Activity_Achievements_Base: onResume");
		
		
		updateUI();
	}
	
	
	private void updateUI() {
		
		LayoutInflater inflater = LayoutInflater.from(this);
		
		//IAchievementsManager achievementsManager = ((Application_Base)getApplication()).getAchievementsManager();
		List<RowItem_IdTD> rows = ((AchievementsProvider_Base)((Application_Base)getApplication()).getEngagementProvider().getAchievementsProvider()).getAchievementsList(getIconSize(), this);//achievementsManager.getAchievementsList(getIconSize(), this);
		ViewGroup frame = ListViewFactory.create_ITD_ByXML_NoChoice(this, inflater, rows, new OnItemClickListener_Menu());
		
		setContentView(frame);
		//setFrame(frame);
		
		setBackgroundPoster(R.id.commons_listview_frame, 77);
		
		frame.invalidate();
	}
	
	
	private class OnItemClickListener_Menu implements
			AdapterView.OnItemClickListener {
		
		
		private OnItemClickListener_Menu() {
			
		}
		
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			
			Intent intent = new Intent(
					Activity_Achievements_Base.this,
					Activity_Picture.class);

			IAchievementsManager achievementsManager = ((Application_Base)getApplication()).getAchievementsManager();
			IConfigurationAchievements[] cfgs = achievementsManager.getAll();
			IConfigurationAchievements cfg = cfgs[position];
			
			Bundle b = new Bundle();
			b.putInt("imageID", cfg.getIconResID());
			
			String title = cfg.getDescription_String();
			if (title == null) {
				title = getString(cfg.getDescription());
			}
			b.putString("imageTitle", title);
			intent.putExtras(b);
			
			startActivity(intent);
			
			//Toast_Base.showToast_InCenter(Activity_Achievements_Base.this, "Show Picture");
		}
	}
	
	
	@Override
	protected int getBackgroundImageID() {
		//boolean left_handed = (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
		//return left_handed ? R.drawable.ic_colours_tube : R.drawable.ic_colours_tube;
		return 0;//R.drawable.ic_achievements;
	}
}
