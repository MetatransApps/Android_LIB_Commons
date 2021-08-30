package org.metatransapps.commons.cfg.difficulty;


import org.metatransapps.commons.cfg.ConfigurationUtils_Base;
import org.metatransapps.commons.cfg.IConfigurationEntry;


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
