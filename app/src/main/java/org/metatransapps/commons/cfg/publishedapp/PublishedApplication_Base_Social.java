package org.metatransapps.commons.cfg.publishedapp;


import org.metatransapps.commons.cfg.appstore.IAppStore;


public abstract class PublishedApplication_Base_Social extends PublishedApplication_Base {
	
	
	public PublishedApplication_Base_Social(IAppStore _store) {
		super(_store, null, null);
	}
	
	
	public PublishedApplication_Base_Social(IAppStore _store, String _app_storeID) {
		super(_store, null, _app_storeID);
	}
	
	
	@Override
	public boolean isSocial() {
		return true;
	}
}
