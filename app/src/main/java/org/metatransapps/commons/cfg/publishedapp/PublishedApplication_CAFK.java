package org.metatransapps.commons.cfg.publishedapp;


import org.metatransapps.commons.R;
import org.metatransapps.commons.cfg.appstore.IAppStore;


public class PublishedApplication_CAFK extends PublishedApplication_Base {
	
	
	public PublishedApplication_CAFK(IAppStore _store) {
		super(_store);
	}
	
	
	public PublishedApplication_CAFK(IAppStore _store, String _app_storeID) {
		super(_store, null, _app_storeID);
	}
	
	
	public PublishedApplication_CAFK(IAppStore _store, String _app_storeID, IPublishedApplication _socialVersion) {
		super(_store, _socialVersion, _app_storeID);
	}
	
	
	public PublishedApplication_CAFK(IAppStore _store, IPublishedApplication _socialVersion) {
		super(_store, _socialVersion, null);
	}
	
	
	@Override
	public String getPackage() {
		return "com.chessartforkids";
	}
	
	
	@Override
	public int getName() {
		return R.string.app_cafk_name;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_logo_cafk;
	}
	
	
	@Override
	public int getDescription_Line1() {
		return R.string.app_cafk_advertising1;
	}
	
	
	@Override
	public int getDescription_Line2() {
		return R.string.app_cafk_advertising2;
	}
}
