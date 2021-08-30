package org.metatransapps.commons.cfg.appstore;


public class AppStore_FDroid_own implements IAppStore {

	@Override
	public int getID() {
		return IAppStore.ID_FDROID_OWN;
	}

	@Override
	public String getName() {
		return "FDroid_own";
	}

}
