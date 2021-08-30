package org.metatrans.commons.cfg.publishedapp;


public class MarketURLGen_Amazon implements IMarketURLGen {
	
	
	private String url;
	
	
	public MarketURLGen_Amazon(String _appID) {
		
		if (_appID == null) {
			
			url = "http://www.amazon.com/s/ref=bl_sr_mobile-apps?_encoding=UTF8&field-brandtextbin=Have%20You%20installed%20it%20yet%3F&node=2350149011";
		} else {
			
			url = "http://www.amazon.com/Have-You-installed-it-yet/dp/" + _appID;
		}
	}
	
	
	@Override
	public String getUrl() {
		return url;
	}
}
