package org.metatrans.commons.engagement.achievements;


import java.util.ArrayList;
import java.util.List;

import org.metatrans.commons.R;
import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.cfg.achievements.IConfigurationAchievements;
import org.metatrans.commons.cfg.colours.IConfigurationColours;
import org.metatrans.commons.engagement.IAchievementsProvider;
import org.metatrans.commons.ui.list.RowItem_IdTD;
import org.metatrans.commons.ui.utils.BitmapUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;


public class AchievementsProvider_Base implements IAchievementsProvider {
	
	
	private Application_Base app;
	
	private static List<RowItem_IdTD> achievementsList;
	private static int achievementsList_iconSize = -1;
	
	
	public AchievementsProvider_Base(Application_Base _app) {
		app = _app;
	}
	
	
	@Override
	public boolean supportAchievementsCounting() {
		return true;
	}
	
	
	@Override
	public void unlock(int achievementID) {
		rebuildAchievementsList(app);
	}
	
	
	@Override
	public void increment(int achievementID, int inc) {
		
		int new_value = app.getAchievementsManager().get(app, achievementID);
		int old_value = new_value - inc;
		
		IConfigurationAchievements cfg = app.getAchievementsManager().getConfigByID(achievementID);
		int increments = app.getResources().getInteger(cfg.getIncrementsCount());
		int max_value = app.getResources().getInteger(cfg.getMaxCount());
		
		if (new_value > 0
				&& (increments == 1 || new_value % increments == 0)
				&& new_value <= increments * max_value) {
			
			rebuildAchievementsList(app);
		}
	}
	
	
	@Override
	public View getAchievementsView(IConfigurationColours coloursCfg, RectF rectf) {
		return null;
	}
	
	
	@Override
	public void openAchievements() {
		
		Activity currentActivity = app.getCurrentActivity();
		
		if (currentActivity != null) {
			Intent i = new Intent(currentActivity, Activity_Achievements_Base.class);
			currentActivity.startActivity(i);
		}
	}
	
	
	public List<RowItem_IdTD> getAchievementsList(int iconSize, Context context) {
		
		if (achievementsList == null) {
			achievementsList_iconSize = iconSize;
			achievementsList = buildAchievementsList(iconSize, context);
		}
		
		return achievementsList;
	}
	
	
	private void rebuildAchievementsList(Context context) {
		if (achievementsList_iconSize != -1) {
			achievementsList = buildAchievementsList(achievementsList_iconSize, context);
		}
	}
	
	
	private List<RowItem_IdTD> buildAchievementsList(int iconSize, Context context) {
		
		List<RowItem_IdTD> rowItems = new ArrayList<RowItem_IdTD>();
		
		IConfigurationAchievements[] cfgs = app.getAchievementsManager().getAll();
		
		for (int i = 0; i < cfgs.length; i++) {
			
			IConfigurationAchievements cfg = cfgs[i];
			
			boolean earned = app.getAchievementsManager().isEarned(context, cfg.getID());
			boolean hidden = app.getAchievementsManager().isHidden(context, cfg.getID());
			
			Bitmap bitmap = null;
			String title = null;
			String description = null;
			
			if (earned) {
				
				Bitmap bitmap_org = BitmapUtils.fromResource(context, cfg.getIconResID(), iconSize);
				bitmap = BitmapUtils.createScaledBitmap(bitmap_org, iconSize, iconSize);
				//bitmap = CachesBitmap.getSingletonIcons(getIconSize()).getBitmap(this, cfg.getIconResID());
				BitmapUtils.recycle(bitmap, bitmap_org);
				
				//title = context.getString(cfg.getName());
				//description = getString(cfg.getDescription());
				
				title = cfg.getDescription_String();
				if (title == null) {
					title = context.getString(cfg.getDescription());
				}
				//description = "";
				
				//NotificationsUtils.createNotification_Achievement(this, cfg.getID());
				
			} else {
				if (hidden) {
					
					bitmap = BitmapUtils.fromResource(context, R.drawable.ic_gift_locked, iconSize);
							//CachesBitmap.getSingletonIcons(iconSize).getBitmap(context, R.drawable.ic_gift_locked);
					bitmap = BitmapUtils.toGrayscale(bitmap);
					
					title = context.getString(R.string.achievements_hidden_title);
					description = "";//getString(R.string.achievements_locked_desc);
					
				} else {
					
					bitmap = BitmapUtils.fromResource(context, R.drawable.ic_gift_locked, iconSize);
					bitmap = BitmapUtils.toGrayscale(bitmap);
					
					title = cfg.getName_String();
					if (title == null) {
						title = context.getString(cfg.getName());
					}
					//description = "";//getString(cfg.getDescription());
				}
			}
			
			int count = app.getAchievementsManager().get(context.getApplicationContext(), cfg.getID());
			int increments = context.getApplicationContext().getResources().getInteger(cfg.getIncrementsCount());
			count = count / increments;
			int maxcount = context.getApplicationContext().getResources().getInteger(cfg.getMaxCount());
			
			int singlescores = context.getResources().getInteger(cfg.getScores());
			int cur_scores = (Math.min(count, maxcount) * singlescores);
			
			description = Math.min(count, maxcount) + " / " + maxcount
					+ "  (" + Math.min(count, maxcount) + " * " + singlescores +" " + context.getString(R.string.scores).toLowerCase()
					+ " = " + cur_scores + " " + context.getString(R.string.scores).toLowerCase() + ")";
			
			
			Drawable drawable = BitmapUtils.createDrawable(context, bitmap);
			
			RowItem_IdTD item = new RowItem_IdTD(earned, drawable, title, description);
			rowItems.add(item);
		}
		
		return rowItems;
	}
}
