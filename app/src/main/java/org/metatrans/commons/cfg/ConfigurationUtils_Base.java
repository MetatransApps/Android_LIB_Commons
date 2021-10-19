package org.metatrans.commons.cfg;


import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;


public class ConfigurationUtils_Base {
	
	
	private static final Map<String, ConfigurationUtils_Base> all_cfgs = new HashMap<String, ConfigurationUtils_Base>();
	
	
	private IConfigurationEntry[] ALL_CFGs;
	//private static String[] ALL_Names;


	@SuppressLint("UseSparseArrays")
	private final Map<Integer, IConfigurationEntry> mapping_id = new HashMap<Integer, IConfigurationEntry>();
	//private static final Map<String, IConfigurationEntry> mapping_name = new HashMap<String, IConfigurationEntry>();


	private boolean initialized = false;
	

	protected static void createInstance(String tag, ConfigurationUtils_Base instance, IConfigurationEntry[] cfgs_entries) {

		if (true) {
			throw new UnsupportedOperationException("Fix Menus!");
		}

		synchronized (all_cfgs) {
			
			ConfigurationUtils_Base new_inst = all_cfgs.get(tag);

			if (new_inst != null) {
				throw new IllegalStateException("CFG instance with tag '" + tag + "' already exists.");
			}
			
			new_inst = instance;

			if (new_inst == null) {

				new_inst = new ConfigurationUtils_Base();
			}

			new_inst.init(cfgs_entries);
			
			all_cfgs.put(tag, new_inst);
		}
	}
	
	
	protected void init(IConfigurationEntry[] cfgs_entries) {
		
		if (!initialized) { 
			
			 ALL_CFGs = cfgs_entries;

			 //ALL_Names = new String[ALL_CFGs.length];


			for (int i=0; i<ALL_CFGs.length; i++) {
				
				Integer id = ALL_CFGs[i].getID();
				//String name = context.getString(ALL_CFGs[i].getName());
				//ALL_Names[i] = name;

				IConfigurationEntry cfg = ALL_CFGs[i];
				
				if (mapping_id.containsKey(id)) {

					throw new IllegalStateException("Duplicated cfg id: " + id);
				}

				mapping_id.put(id, cfg);
				
				//if (mapping_name.containsKey(id)) {
				//	throw new IllegalStateException("Duplicated cfg name: " + id);
				//}
				
				//mapping_name.put(name, cfg);
			}
			
			initialized = true;
		}
	}


	public static ConfigurationUtils_Base getInstance(String tag) {

		synchronized (all_cfgs) {

			ConfigurationUtils_Base cur = all_cfgs.get(tag);

			if (cur == null) {

				throw new IllegalStateException("CFG instance with tag '" + tag + "' not found.");
			}

			return cur;
		}
	}


	public IConfigurationEntry[] getAll() {
		return ALL_CFGs;
	}

	
	/*public static String[] getNames() {
		return ALL_Names;
	}
	*/
	
	public int getOrderNumber(int cfgID) {

		for (int i=0; i<ALL_CFGs.length; i++) {

			Integer id = ALL_CFGs[i].getID();

			if (id == cfgID) {

				return i;
			}
		}

		throw new IllegalStateException("CFG identifier " + cfgID + " not found.");
	}
	
	
	public int getID(int orderNumber) {
		return ALL_CFGs[orderNumber].getID();
	}
	
	
	public IConfigurationEntry getConfigByID(int id) {
		
		IConfigurationEntry result = mapping_id.get(id);
		
		if (result == null) {
			throw new IllegalStateException("Config with id = " + id + " not found.");
		}
		
		return result;
	}
	
	
	public int getNextConfigID(int id) {
		
		int nextIndex = -1;
		for (int i=0; i<getAll().length; i++) {
			if (getAll()[i].getID() == id) {
				nextIndex = i + 1;
				break;
			}
		}
		
		if (nextIndex > getAll().length - 1) {
			nextIndex = getAll().length - 1;
		}
		
		return getAll()[nextIndex].getID();
	}
}
