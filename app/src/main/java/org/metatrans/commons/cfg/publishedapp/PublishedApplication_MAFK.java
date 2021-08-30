package org.metatrans.commons.cfg.publishedapp;


import org.metatrans.commons.R;
import org.metatrans.commons.cfg.appstore.IAppStore;


public class PublishedApplication_MAFK extends PublishedApplication_Base {
	
	
	public PublishedApplication_MAFK(IAppStore _store, IPublishedApplication _socialVersion) {
		super(_store, _socialVersion, null);
	}

	
	public PublishedApplication_MAFK(IAppStore _store, String _app_storeID, IPublishedApplication _socialVersion) {
		super(_store, _socialVersion, _app_storeID);
	}
	
	
	public PublishedApplication_MAFK(IAppStore _store) {
		super(_store);
	}
	
	
	public PublishedApplication_MAFK(IAppStore _store, String _app_storeID) {
		super(_store, _app_storeID);
	}
	
	
	@Override
	public String getPackage() {
		return "com.mathforkids5";
	}
	
	
	@Override
	public int getName() {
		return R.string.app_mafk_name;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_logo_mafk;
	}
	
	
	@Override
	public int getDescription_Line1() {
		return R.string.app_mafk_advertising1;
	}
	
	
	@Override
	public int getDescription_Line2() {
		return R.string.app_mafk_advertising2;
	}
}
