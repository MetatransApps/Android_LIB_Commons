package com.apps.mobile.android.commons.cfg.appstore;


public class AppStore_FDroid_official implements IAppStore {

	@Override
	public int getID() {
		return IAppStore.ID_FDROID_OFFICIAL;
	}

	@Override
	public String getName() {
		return "FDroid_official";
	}

}
