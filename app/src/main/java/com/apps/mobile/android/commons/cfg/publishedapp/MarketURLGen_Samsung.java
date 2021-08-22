package com.apps.mobile.android.commons.cfg.publishedapp;


public class MarketURLGen_Samsung implements IMarketURLGen {
	
	
	private String url;
	
	
	public MarketURLGen_Samsung(String _appID) {
		
		if (_appID == null) {
			
			url = "http://apps.samsung.com";
		} else {
		
			url = "http://apps.samsung.com/earth/topApps/topAppsDetail.as?productId=" + _appID;
		}
	}
	
	
	@Override
	public String getUrl() {
		return url;
	}
}
