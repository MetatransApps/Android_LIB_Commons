package org.metatrans.commons.cfg.sound;


import android.annotation.SuppressLint;

import org.metatrans.commons.cfg.colours.Config_Colours_BlueDove;
import org.metatrans.commons.cfg.colours.Config_Colours_BluePetrol;
import org.metatrans.commons.cfg.colours.Config_Colours_BlueSkyOnGray;
import org.metatrans.commons.cfg.colours.Config_Colours_Brown;
import org.metatrans.commons.cfg.colours.Config_Colours_Gray;
import org.metatrans.commons.cfg.colours.Config_Colours_GreenCool;
import org.metatrans.commons.cfg.colours.Config_Colours_GreenWarm;
import org.metatrans.commons.cfg.colours.Config_Colours_RedCool;
import org.metatrans.commons.cfg.colours.Config_Colours_RedWarm;
import org.metatrans.commons.cfg.colours.Config_Colours_VioletMauve;

import java.util.HashMap;
import java.util.Map;


public class ConfigurationUtils_Sound {
	
	
	private static final IConfigurationSound[] ALL_CFGs = new IConfigurationSound[] {
		new Config_Sound_Base(false),
		new Config_Sound_Base(true),
	};
	
	
	@SuppressLint("UseSparseArrays")
	private static final Map<Integer, IConfigurationSound> mapping = new HashMap<Integer, IConfigurationSound>();
	
	
	static {
		for (int i=0; i<ALL_CFGs.length; i++) {

			IConfigurationSound cfg = ALL_CFGs[i];
			Integer id = cfg.getID();

			if (mapping.containsKey(id)) {
				throw new IllegalStateException("Duplicated cfg id: " + id);
			}
			
			mapping.put(id, cfg);
		}
	}
	
	
	public static IConfigurationSound[] getAll() {
		return ALL_CFGs;
	}
	
	
	public static int getID(int orderNumber) {
		return ALL_CFGs[orderNumber].getID();
	}
	
	
	public static IConfigurationSound getConfigByID(int id) {
		
		IConfigurationSound result = mapping.get(id);
		
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
