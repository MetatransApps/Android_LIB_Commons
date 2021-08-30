package org.metatransapps.commons.cfg.appstore;


public class AppStore_Huawei implements IAppStore {

	@Override
	public int getID() {
		return IAppStore.ID_HUAWEI;
	}

	@Override
	public String getName() {
		return "Huawei";
	}

}
