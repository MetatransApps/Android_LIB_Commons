package org.metatrans.commons.cfg.publishedapp;


public class MarketURLGen_FDroidOfficial implements IMarketURLGen {


	private String url;


	public MarketURLGen_FDroidOfficial(String _appID) {

		url = "https://f-droid.org/packages/" + _appID + "/";
	}
	
	
	@Override
	public String getUrl() {
		return url;
	}
}
