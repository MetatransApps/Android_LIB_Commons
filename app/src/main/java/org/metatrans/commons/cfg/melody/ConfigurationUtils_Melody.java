package org.metatrans.commons.cfg.melody;


import android.annotation.SuppressLint;

import org.metatrans.commons.R;

import java.util.HashMap;
import java.util.Map;


public class ConfigurationUtils_Melody {
	
	
	private static final IConfigurationMelody[] ALL_CFGs = new IConfigurationMelody[] {
		new Config_Melody_Base(IConfigurationMelody.CFG_MELODY_NONE, R.string.melody_none),
		new Config_Melody_Base(IConfigurationMelody.CFG_MELODY_ADVENTURE, R.string.melody_adventure),
		new Config_Melody_Base(IConfigurationMelody.CFG_MELODY_BATTLE, R.string.melody_battle),
		new Config_Melody_Base(IConfigurationMelody.CFG_MELODY_SPACE_V1, R.string.melody_space_v1),
		new Config_Melody_Base(IConfigurationMelody.CFG_MELODY_SPACE_V2, R.string.melody_space_v2),
		new Config_Melody_Base(IConfigurationMelody.CFG_MELODY_CREATIVITY, R.string.melody_creativity),
		new Config_Melody_Base(IConfigurationMelody.CFG_MELODY_RELAXATION, R.string.melody_relaxation),
	};
	
	
	@SuppressLint("UseSparseArrays")
	private static final Map<Integer, IConfigurationMelody> mapping = new HashMap<Integer, IConfigurationMelody>();
	
	
	static {
		for (int i=0; i<ALL_CFGs.length; i++) {

			IConfigurationMelody cfg = ALL_CFGs[i];
			Integer id = cfg.getID();

			if (mapping.containsKey(id)) {
				throw new IllegalStateException("Duplicated cfg id: " + id);
			}
			
			mapping.put(id, cfg);
		}
	}
	
	
	public static IConfigurationMelody[] getAll() {
		return ALL_CFGs;
	}
	
	
	public static int getID(int orderNumber) {
		return ALL_CFGs[orderNumber].getID();
	}
	
	
	public static IConfigurationMelody getConfigByID(int id) {

		IConfigurationMelody result = mapping.get(id);
		
		if (result == null) {
			throw new IllegalStateException("Config with id = " + id + " not found.");
		}
		
		return result;
	}
	
	
	public static int getOrderNumber(int cfgID) {
		for (int i=0; i<ALL_CFGs.length; i++) {
			Integer id = ALL_CFGs[i].getID();
			if (id == cfgID) {
				return i;
			}
		}

		throw new IllegalStateException("CFG identifier " + cfgID + " not found.");
	}
}
