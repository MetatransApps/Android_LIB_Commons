package org.metatrans.commons.cfg.publishedapp;


public class MarketURLGen_Amazon implements IMarketURLGen {
	
	
	private String url;
	
	
	public MarketURLGen_Amazon(String _appID) {

		url = "amzn://apps/android?p=" + _appID;
	}
	
	
	@Override
	public String getUrl() {
		return url;
	}
}
