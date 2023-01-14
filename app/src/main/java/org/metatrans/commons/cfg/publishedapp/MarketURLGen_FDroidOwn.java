package org.metatrans.commons.cfg.publishedapp;


public class MarketURLGen_FDroidOwn implements IMarketURLGen {


	private String url;


	public MarketURLGen_FDroidOwn(String _appID) {

		url = "https://metatransapps.com/fdroid/";
		//url = "https://fdroid.metatransapps.com/fdroid/repo/index.html";
	}
	
	
	@Override
	public String getUrl() {
		return url;
	}
}
