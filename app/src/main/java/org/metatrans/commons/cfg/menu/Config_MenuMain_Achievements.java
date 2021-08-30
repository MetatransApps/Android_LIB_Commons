package org.metatrans.commons.cfg.menu;


import org.metatrans.commons.R;


public class Config_MenuMain_Achievements extends Config_MenuMain_Base {
	
	
	@Override
	public int getID() {
		return CFG_MENU_ACHIEVEMENTS;
	}
	
	
	@Override
	public int getName() {
		return R.string.achievements;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_achievements;
	}
	
	
	@Override
	public int getDescription() {
		return 0;
	}


	@Override
	public Runnable getAction() {
		throw new UnsupportedOperationException();
	}
}
