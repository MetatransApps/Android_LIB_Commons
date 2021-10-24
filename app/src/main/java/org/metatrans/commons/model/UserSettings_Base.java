package org.metatrans.commons.model;


import org.metatrans.commons.app.Application_Base;

import java.io.Serializable;


public class UserSettings_Base implements Serializable {
	
	
	private static final long serialVersionUID = 4963503750930768510L;
	
	
	public static final String FILE_NAME_USER_SETTINGS 		= "user_settings";
	
	
	public int uiColoursID;

	public int modeID;
	
	
	protected UserSettings_Base() {
		
	}


	public void save() {
		Application_Base.getInstance().storeUserSettings();
	}
}
