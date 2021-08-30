package org.metatransapps.commons.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class AchievementsData implements Serializable {
	
	
	private static final long serialVersionUID = 1005932031867911730L;
	
	
	private Map<Integer, Integer> achievements;
	private List<Integer> notifications;
	
	
	public AchievementsData() {
		achievements = new HashMap<Integer, Integer>();
		notifications = new Vector<Integer>();
	}
	
	
	/*public void inc(int id) {
		inc(id, 1);
	}*/
	
	
	public void inc(int id, int increment) {
		Integer value = achievements.get(id);
		if (value == null) {
			value = Integer.valueOf(0);
			achievements.put(id, value);
		}
		achievements.put(id, achievements.get(id) + increment);
	}
	
	
	public int get(int id) {
		Integer value = achievements.get(id);
		if (value == null) {
			value = Integer.valueOf(0);
		}
		return value;
	}
	
	
	public Integer getNextNotification() {
		if (notifications.size() > 0) {
			return notifications.remove(0);	
		}
		return null;
	}

	
	public List<Integer> getNotifications() {
		
		List<Integer> copy = new ArrayList<Integer>();
		
		copy.addAll(notifications);
		notifications.clear();
		
		return copy;
	}
	
	
	public void addNotification(int achievementID) {
		notifications.add(achievementID);
	}
}
