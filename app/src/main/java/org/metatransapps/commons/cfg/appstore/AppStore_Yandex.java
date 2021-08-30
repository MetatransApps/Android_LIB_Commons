package org.metatransapps.commons.cfg.appstore;


public class AppStore_Yandex implements IAppStore {

	@Override
	public int getID() {
		return IAppStore.ID_YANDEX;
	}

	@Override
	public String getName() {
		return "Yandex";
	}

}
