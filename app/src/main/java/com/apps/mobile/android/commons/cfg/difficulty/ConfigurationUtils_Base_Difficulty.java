package com.apps.mobile.android.commons.cfg.difficulty;


import com.apps.mobile.android.commons.cfg.ConfigurationUtils_Base;
import com.apps.mobile.android.commons.cfg.IConfigurationEntry;


public class ConfigurationUtils_Base_Difficulty extends ConfigurationUtils_Base {
	
	
	public IConfigurationDifficulty[] getAll() {
		
		IConfigurationEntry[] entries = super.getAll();
		
		IConfigurationDifficulty[] difficulties = new IConfigurationDifficulty[entries.length];
		for (int i=0; i<entries.length; i++) {
			difficulties[i] = (IConfigurationDifficulty) entries[i];
		}
		
		return difficulties;
	}
	
	
	public IConfigurationDifficulty getConfigByID(int id) {
		return (IConfigurationDifficulty) super.getConfigByID(id);
	}
}
