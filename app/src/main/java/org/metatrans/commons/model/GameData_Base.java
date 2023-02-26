package org.metatrans.commons.model;


import org.metatrans.commons.app.Application_Base;

import java.io.Serializable;


public class GameData_Base implements Serializable {
	
	
	private static final long serialVersionUID = 7228947244491935938L;
	
	
	public static final String FILE_NAME_GAME_DATA 		= "game_data";


	public static final int MODEL_VERSION_LATEST 	= 3;


	public int model_version = MODEL_VERSION_LATEST;

	public long timestamp_created;
	public boolean counted_as_started_game;
	public boolean counted_as_completed_game;
	public boolean counted_as_exited_game;
	
	public long accumulated_time_in_main_screen;
	
	
	public GameData_Base() {

		timestamp_created = System.currentTimeMillis();

		UserData_Base user_data = Application_Base.getInstance().getUserData();
		user_data.count_played_games++;
		user_data.save();
		System.out.println("GameData_Base.init(): UserData.count_played_games=" + user_data.count_played_games);
	}
	
	
	public void save() {
		Application_Base.getInstance().storeGameData(this);
	}
	
	
	public long getAccumulated_time_inmainscreen() {
		return accumulated_time_in_main_screen;
	}
	
	
	public void addAccumulated_time_inmainscreen(long accumulated_time_in_main_screen) {
		this.accumulated_time_in_main_screen += accumulated_time_in_main_screen;
	}


	public long getCreatedAt() {
		return timestamp_created;
	}
	

	public boolean isCountedAsStarted() {
		return counted_as_started_game;
	}
	
	
	public void setCountedAsStarted() {
		counted_as_started_game = true;
	}
	
	
	public boolean isCountedAsCompleted() {
		return counted_as_completed_game;
	}
	
	
	public void setCountedAsCompleted() {
		counted_as_completed_game = true;
	}
	
	
	public boolean isCountedAsExited() {
		return counted_as_exited_game;
	}
	
	
	public void setCountedAsExited() {
		counted_as_exited_game = true;
	}
}
