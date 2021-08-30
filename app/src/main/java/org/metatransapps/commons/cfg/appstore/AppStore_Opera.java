package org.metatransapps.commons.cfg.appstore;


public class AppStore_Opera implements IAppStore {

	@Override
	public int getID() {
		return IAppStore.ID_OPERA;
	}

	@Override
	public String getName() {
		return "Opera";
	}

}
