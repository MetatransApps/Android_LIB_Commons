package org.metatrans.commons.cfg.colours;


import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;


public class ConfigurationUtils_Colours {
	
	
	private static final IConfigurationColours[] ALL_CFGs = new IConfigurationColours[] {
		new Config_Colours_Gray(),
		new Config_Colours_GreenWarm(),
		new Config_Colours_BluePetrol(),
		new Config_Colours_Brown(),
		new Config_Colours_RedWarm(),
		new Config_Colours_BlueDove(),
		new Config_Colours_VioletMauve(),
		new Config_Colours_RedCool(),
		new Config_Colours_GreenCool(),
		new Config_Colours_BlueSkyOnGray(),
		new Config_Colours_AutoByBaseColor(12, 186,225,255),
		new Config_Colours_AutoByBaseColor(13, 186,255,201),
		//new Config_Colours_AutoByBaseColor(14, 255,255,186), //Too light yellow
		new Config_Colours_AutoByBaseColor(15, 255,223,186),
		new Config_Colours_AutoByBaseColor(16, 255,179,186),
		//new Config_Colours_AutoByBaseColor(17, 160, 221, 160),

	};
	
	
	@SuppressLint("UseSparseArrays")
	private static final Map<Integer, IConfigurationColours> mapping = new HashMap<Integer, IConfigurationColours>();
	
	
	static {
		for (int i=0; i<ALL_CFGs.length; i++) {

			IConfigurationColours cfg = ALL_CFGs[i];
			Integer id = cfg.getID();

			if (mapping.containsKey(id)) {
				throw new IllegalStateException("Duplicated cfg id: " + id);
			}
			
			mapping.put(id, cfg);
		}
	}
	
	
	public static IConfigurationColours[] getAll() {
		return ALL_CFGs;
	}
	
	
	public static int getID(int orderNumber) {
		return ALL_CFGs[orderNumber].getID();
	}
	
	
	public static IConfigurationColours getConfigByID(int id) {
		
		IConfigurationColours result = mapping.get(id);
		
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
