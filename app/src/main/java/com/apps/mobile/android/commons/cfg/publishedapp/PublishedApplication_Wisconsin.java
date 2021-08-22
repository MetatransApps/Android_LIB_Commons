package com.apps.mobile.android.commons.cfg.publishedapp;


import com.apps.mobile.android.commons.R;
import com.apps.mobile.android.commons.cfg.appstore.IAppStore;


public class PublishedApplication_Wisconsin extends PublishedApplication_Base {
	
	
	public PublishedApplication_Wisconsin(IAppStore _store) {
		super(_store);
	}
	
	
	public PublishedApplication_Wisconsin(IAppStore _store, String _app_storeID) {
		super(_store, _app_storeID);
	}
	
	
	@Override
	public String getPackage() {
		return "com.wisconsin";
	}
	
	
	@Override
	public int getName() {
		return R.string.app_wisconsin_name;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_logo_wcst;
	}
	
	
	@Override
	public int getDescription_Line1() {
		return R.string.app_wisconsin_advertising1;
	}
	
	
	@Override
	public int getDescription_Line2() {
		return R.string.app_wisconsin_advertising2;
	}
}
