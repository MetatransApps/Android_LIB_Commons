package org.metatransapps.commons.cfg.appstore;


public class AppStore_Default implements IAppStore {

	@Override
	public int getID() {
		return IAppStore.ID_DEFAULT;
	}

	@Override
	public String getName() {
		return "Default";
	}

}
