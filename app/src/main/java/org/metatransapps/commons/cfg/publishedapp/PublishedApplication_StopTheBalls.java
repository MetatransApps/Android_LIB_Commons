package org.metatransapps.commons.cfg.publishedapp;


import org.metatransapps.commons.R;
import org.metatransapps.commons.cfg.appstore.IAppStore;


public class PublishedApplication_StopTheBalls extends PublishedApplication_Base {
	
	
	public PublishedApplication_StopTheBalls(IAppStore _store) {
		super(_store);
	}
	
	
	public PublishedApplication_StopTheBalls(IAppStore _store, String _app_storeID) {
		super(_store, _app_storeID);
	}
	
	
	@Override
	public String getPackage() {
		return "com.stoptheballs";
	}
	
	
	@Override
	public int getName() {
		return R.string.app_stb_name;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_logo_balls;
	}
	
	
	@Override
	public int getDescription_Line1() {
		return R.string.app_stb_advertising1;
	}
	
	
	@Override
	public int getDescription_Line2() {
		return R.string.app_stb_advertising2;
	}
}
