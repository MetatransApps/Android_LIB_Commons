package org.metatrans.commons.achievements;


import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.cfg.achievements.IConfigurationAchievements;
import org.metatrans.commons.engagement.IEngagementProvider;
import org.metatrans.commons.model.AchievementsData;
import org.metatrans.commons.storage.StorageUtils;

import android.content.Context;


public class AchievementsManager_Base implements IAchievementsManager {
	
	
	private static final String FILE_NAME = "commons_achievements";
	
	private Object cachedSync;
	
	private Application_Base app;
	private IEngagementProvider engagementProvider;
	
	
	public AchievementsManager_Base(Application_Base _app) {
		
		cachedSync = new Object();
		
		app = _app;
		engagementProvider = app.getEngagementProvider();
	}
	
	
	public IConfigurationAchievements[] getAll() {
		return new IConfigurationAchievements[0];
	}
	
	
	public void sentNotification(Context context, Integer achievementID) {
		
	}
	
	
	public IConfigurationAchievements getConfigByID(int cfgID) {
		
		for (int i=0; i<getAll().length; i++) {
			Integer cur_id = getAll()[i].getID();
			if (cur_id == cfgID) {
				return getAll()[i];
			}
		}
		
		throw new IllegalStateException("CFG identifier " + cfgID + " not found.");
	}
	
	
	@Override
	public IConfigurationAchievements getConfigByRefID(String refID) {
		
		for (int i=0; i<getAll().length; i++) {
			String cur_refid = app.getString( getAll()[i].geIDReference() );
			if (cur_refid.equals(refID)) {
				return getAll()[i];
			}
		}
		
		return null;
	}
	
	
	public String countEarned(Context activity) {
		
		IConfigurationAchievements[] cfgs = getAll();
		
		if (engagementProvider.getAchievementsProvider().supportAchievementsCounting()) {
			
			int earnedCount = 0;
			
			synchronized (cachedSync) {
				
				for (int i = 0; i < cfgs.length; i++) {
				
					IConfigurationAchievements cfg = cfgs[i];
					boolean earned = isEarned(activity, cfg.getID());
					if (earned) earnedCount++; 
				}
			}
			
			return earnedCount + " / " + cfgs.length;
			
		} else {
			
			return "?" + " / " + cfgs.length;
		}
		
	}
	
	
	public boolean isEarned(Context activity, int achievementID) {
		
		int value = 0;
		
		synchronized (cachedSync) {
			value = get(activity, achievementID);
		}
		
		IConfigurationAchievements cfg = getConfigByID(achievementID);
		
		return value >= activity.getResources().getInteger(cfg.getIncrementsCount());
		
	}
	
	
	public boolean isHidden(Context activity, int achievementID) {
		
		synchronized (cachedSync) {
			
			IConfigurationAchievements cfg = getConfigByID(achievementID);
			
			return cfg.isHidden();
		}
	}
	

	private boolean isOneTime(Context activity, int achievementID) {
		
		IConfigurationAchievements cfg = getConfigByID(achievementID);
		
		return (activity.getResources().getInteger(cfg.getIncrementsCount()) * activity.getResources().getInteger(cfg.getMaxCount())) == 1;
		
	}
	
	
	private final boolean isSupported(int achievementID) {
		IConfigurationAchievements[] all = getAll();
		for (int i=0; i<all.length; i++) {
			if (all[i].getID() == achievementID) {
				return true;
			}
		}
		return false;
	}
	
	
	public void inc(Context context, int achievementID) {
		inc(context, achievementID, 1);
	}
	
	
	public void inc(Context context, int achievementID, int increment) {
		inc(context, achievementID, increment, true);
	}


	public void inc(Context context, int achievementID, int increment, boolean call_engagementProvider) {
		
		synchronized (cachedSync) {
			//System.out.println("ACHI: inc achievementID=" + achievementID);
			
			if (!isSupported(achievementID)) {
				return;
			}
			
			//System.out.println("ACHI: inc supported");
			
			try {
				
				AchievementsData data = readFromStorage(context);
				//System.out.println("ACHI: inc data=" + data);
				if (data == null) {
					data = new AchievementsData();
					StorageUtils.writeStore(context, FILE_NAME, data);
					data = readFromStorage(context);
				}
				
				boolean earned_b = isEarned(context, achievementID);
				
				data.inc(achievementID, increment);
				writeToStorage(context);
				
				boolean earned_a = isEarned(context, achievementID);
				
				//System.out.println("ACHIEVEMENTS: inc earned_b=" + earned_b + ", earned_a=" + earned_a);
				
				/*if (!earned_b && earned_a) {
					
					//System.out.println("ACHI: inc added notification");
					
					data.addNotification(achievementID);
					
					writeToStorage(context);
				}*/
				
				if (call_engagementProvider) {
					
					if (isOneTime(context, achievementID)) {
						
						IConfigurationAchievements cfg = getConfigByID(achievementID);
						int max_value = context.getResources().getInteger(cfg.getMaxCount());
						int new_value = get(context, achievementID);
						
						if (new_value >= max_value) {
							engagementProvider.getAchievementsProvider().unlock(achievementID);
						}
					} else {
						engagementProvider.getAchievementsProvider().increment(achievementID, increment);
					}
				}
				
				if (!earned_b && earned_a) {
					data.addNotification(achievementID);
					writeToStorage(context);
				}
				
				cachedSync.notifyAll();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private static void writeToStorage(Context activity) {
		StorageUtils.writeStore(activity, FILE_NAME);
	}


	private static AchievementsData readFromStorage(Context activity) throws Exception {
		return (AchievementsData) StorageUtils.readStorage(activity, FILE_NAME);
	}
	
	
	public int get(Context activity, int achievementID) {
		
		synchronized (cachedSync) {
			try {
				
				AchievementsData data = readFromStorage(activity);
				
				if (data == null) {
					//System.out.println("ACHI: get achievementID=" + achievementID + " = 0");
					return 0;
				}
				
				//System.out.println("ACHI: get achievementID=" + achievementID + " = " + data.get(achievementID));
				
				return data.get(achievementID);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	
	
	public void checkNotifications(Context context) {
		
		while (true) {
			
			synchronized (cachedSync) {
				
				try {
					
					AchievementsData data = readFromStorage(context);
					
					//System.out.println("ACHI: checkNotifications data=" + data);
					
					if (data != null) {
					
						Integer achievementID = data.getNextNotification();
						//System.out.println("ACHI: checkNotifications achievementIDs=" + achievementIDs);
						if (achievementID != null) {
							
							writeToStorage(context);
							
							//congratsDialog(activity, icon_size);
							//throw new IllegalStateException("Notifications not implemented");
							sentNotification(context, achievementID);
							//NotificationsUtils.createNotification_Achievement(context, achievementID);
						}
					}
					
					cachedSync.wait();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/*
	private static void congratsDialog(final Activity activity, int icon_size) {
		
		final AlertDialog d = Alerts.createAlertDialog_CongratsForAchievements(activity, icon_size);
		
		d.setOnShowListener(new DialogInterface.OnShowListener() {
			
		    @Override
		    public void onShow(DialogInterface dialog) {
		    	
		        Button b_p = d.getButton(AlertDialog.BUTTON_POSITIVE);
		        b_p.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View view) {
		            	
						//achievements
						Intent i = new Intent(activity, Activity_Achievements_Base.class);
						activity.startActivity(i);
						
						d.dismiss();
		            }
		        });
		        
		        Button b_neut = d.getButton(AlertDialog.BUTTON_NEUTRAL);
		        b_neut.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View view) {
						
		            	d.dismiss();
		            }
		        });
		    }
		});
		
		d.show();
	}
	*/


	public int getMaxScores(Context context) {
		
		int scores = 1000;
		
		synchronized (cachedSync) {
			
			try {
				AchievementsData data = readFromStorage(context);
				
				if (data == null) {
					data = new AchievementsData();
					StorageUtils.writeStore(context, FILE_NAME, data);
					data = readFromStorage(context);
				}
				
				scores = 0;
				
				IConfigurationAchievements[] all = getAll();
				for (int i=0; i<all.length; i++) {
					IConfigurationAchievements cfg = all[i];
					if (cfg.getScores() > 0) {
						int maxcount = context.getResources().getInteger(cfg.getMaxCount());
						int singlescores = context.getResources().getInteger(cfg.getScores());
						int cur_scores = (maxcount * singlescores);
						scores += cur_scores;
					}
				}
				System.out.println("MAX SCORES: " + scores);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return scores;
	}
	
	
	public int getScores(Context context) {
		
		int scores = 0;
		
		synchronized (cachedSync) {
			
			try {
				AchievementsData data = readFromStorage(context);
				
				if (data == null) {
					//System.out.println("ACHI: get achievementID=" + achievementID + " = 0");
					return scores;
				}
				
				IConfigurationAchievements[] all = getAll();
				for (int i=0; i<all.length; i++) {
					IConfigurationAchievements cfg = all[i];
					if (cfg.getScores() > 0) {
						int count = data.get(cfg.getID());
						int maxcount = context.getResources().getInteger(cfg.getMaxCount());
						int singlescores = context.getResources().getInteger(cfg.getScores());
						int increments = context.getResources().getInteger(cfg.getIncrementsCount());
						int cur_scores = (Math.min(count / increments, maxcount) * singlescores);
						scores += cur_scores;
						System.out.println("id=" + cfg.getID() + ", name=" + context.getString(cfg.getName())
								+ ", count=" + count + ", maxcount=" + maxcount + ", increments=" + increments + ", single scores=" + singlescores + ", cur_scores=" + cur_scores);
					}
				}
				System.out.println("TOTAL SCORES: " + scores);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return scores;
	}


	protected Application_Base getApp() {
		return app;
	}
}
