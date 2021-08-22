package com.apps.mobile.android.commons.cfg.publishedapp;


import com.apps.mobile.android.commons.R;
import com.apps.mobile.android.commons.cfg.appstore.IAppStore;


public class PublishedApplication_MOS extends PublishedApplication_Base {
	
	
	public PublishedApplication_MOS(IAppStore _store) {
		super(_store);
	}
	
	
	public PublishedApplication_MOS(IAppStore _store, String _app_storeID) {
		super(_store, _app_storeID);
	}
	
	
	@Override
	public String getPackage() {
		return "com.maze_squirrel";
	}
	
	
	@Override
	public int getName() {
		return R.string.app_mos_name;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_logo_maze;
	}
	
	
	@Override
	public int getDescription_Line1() {
		return R.string.app_mos_advertising1;
	}
	
	
	@Override
	public int getDescription_Line2() {
		return R.string.app_mos_advertising2;
	}
}
