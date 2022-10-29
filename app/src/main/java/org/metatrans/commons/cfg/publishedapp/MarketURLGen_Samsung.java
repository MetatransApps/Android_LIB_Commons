package org.metatrans.commons.cfg.publishedapp;


public class MarketURLGen_Samsung implements IMarketURLGen {
	
	
	private String url;
	
	
	public MarketURLGen_Samsung(String _appID) {

		url = "samsungapps://ProductDetail/" + _appID;
	}
	
	
	@Override
	public String getUrl() {
		return url;
	}
}
