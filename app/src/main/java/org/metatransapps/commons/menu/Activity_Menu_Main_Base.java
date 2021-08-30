package org.metatransapps.commons.menu;


import java.util.ArrayList;
import java.util.List;

import org.metatransapps.commons.cfg.IConfigurationEntry;
import org.metatransapps.commons.cfg.menu.ConfigurationUtils_Base_MenuMain;
import org.metatransapps.commons.cfg.menu.IConfigurationMenu_Main;


public class Activity_Menu_Main_Base extends Activity_Menu_Base {
	
	
	protected List<IConfigurationMenu_Main> getEntries() {
		
		List<IConfigurationMenu_Main> result = new ArrayList<IConfigurationMenu_Main>();
		
		IConfigurationEntry[] common_entries_arr = ConfigurationUtils_Base_MenuMain.getInstance().getAll();
		for (int i = 0; i < common_entries_arr.length; i++) {
			result.add((IConfigurationMenu_Main) common_entries_arr[i]);
		}
		
		return result;
	}
}
