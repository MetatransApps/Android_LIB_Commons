package org.metatransapps.commons.cfg.appstore;


public class AppStore_Samsung implements IAppStore {

	@Override
	public int getID() {
		return IAppStore.ID_SAMSUNG;
	}

	@Override
	public String getName() {
		return "Samsung";
	}

}
