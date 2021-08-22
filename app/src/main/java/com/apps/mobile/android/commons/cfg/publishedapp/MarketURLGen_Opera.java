package com.apps.mobile.android.commons.cfg.publishedapp;


public class MarketURLGen_Opera implements IMarketURLGen {
	
	
	private String url;
	
	
	public MarketURLGen_Opera(String _appID) {
		
		if (_appID == null) {
			
			url = "http://apps.opera.com/badge.php?a=c&v=dark_v2&did=151772";
		} else {
		
			url = "http://apps.opera.com/badge.php?a=c&v=dark_v2&did=151772&pid=" + _appID;
		}
	}
	
	
	@Override
	public String getUrl() {
		return url;
	}
}
