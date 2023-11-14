package org.metatrans.commons.model;


import org.metatrans.commons.app.Application_Base;

import java.io.Serializable;


public class UserSettings_Base implements Serializable {
	
	
	private static final long serialVersionUID = 4963503750930768510L;
	
	
	public static final String FILE_NAME_USER_SETTINGS 		= "user_settings";


	public static final int MODEL_VERSION_LATEST 			= 1;


	public int model_version = MODEL_VERSION_LATEST;

	public int uiColoursID;

	public int modeID;

    public boolean dont_show_alert_rate_app;

	public int melody_cfg_id;

	public int common_sound_cfg_id; //0 or 1

    protected UserSettings_Base() {
		
	}

	public void save() {
		Application_Base.getInstance().storeUserSettings(this);
	}
}
