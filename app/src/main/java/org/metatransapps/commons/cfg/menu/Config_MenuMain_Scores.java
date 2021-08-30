package org.metatransapps.commons.cfg.menu;


import org.metatransapps.commons.R;


public class Config_MenuMain_Scores extends Config_MenuMain_Base {
	
	
	@Override
	public int getID() {
		return CFG_MENU_SCORES;
	}
	
	
	@Override
	public int getName() {
		return R.string.scores;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_scores;
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
