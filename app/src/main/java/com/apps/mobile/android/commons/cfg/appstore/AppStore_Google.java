package com.apps.mobile.android.commons.cfg.appstore;


public class AppStore_Google implements IAppStore {

	@Override
	public int getID() {
		return IAppStore.ID_GOOGLE;
	}

	@Override
	public String getName() {
		return "Google";
	}

}
