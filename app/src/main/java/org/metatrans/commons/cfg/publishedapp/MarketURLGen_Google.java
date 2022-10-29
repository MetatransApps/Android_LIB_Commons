package org.metatrans.commons.cfg.publishedapp;


public class MarketURLGen_Google implements IMarketURLGen {
	
	
	private String url;
	
	
	public MarketURLGen_Google(String _appID) {

		url = "https://play.google.com/store/apps/details?id=" + _appID;
	}
	
	
	@Override
	public String getUrl() {
		return url;
	}
}
