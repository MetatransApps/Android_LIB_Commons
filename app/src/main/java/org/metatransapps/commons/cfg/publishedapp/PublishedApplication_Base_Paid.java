package org.metatransapps.commons.cfg.publishedapp;


import org.metatransapps.commons.cfg.appstore.IAppStore;


public abstract class PublishedApplication_Base_Paid extends PublishedApplication_Base {
	
	
	public PublishedApplication_Base_Paid(IAppStore _store) {
		super(_store, null, null);
	}
	
	
	public PublishedApplication_Base_Paid(IAppStore _store, String _app_storeID) {
		super(_store, null, _app_storeID);
	}
	
	
	@Override
	public boolean isPaid() {
		return true;
	}
}
