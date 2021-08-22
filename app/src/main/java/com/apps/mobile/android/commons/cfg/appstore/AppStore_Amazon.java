package com.apps.mobile.android.commons.cfg.appstore;


public class AppStore_Amazon implements IAppStore {

	@Override
	public int getID() {
		return IAppStore.ID_AMAZON;
	}

	@Override
	public String getName() {
		return "Amazon";
	}

}
