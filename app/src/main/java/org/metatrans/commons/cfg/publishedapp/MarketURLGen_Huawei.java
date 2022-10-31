package org.metatrans.commons.cfg.publishedapp;


public class MarketURLGen_Huawei implements IMarketURLGen {


	private String url;


	public MarketURLGen_Huawei(String _appID) {
			
		url = "market://details?id=" + _appID;
	}
	
	
	@Override
	public String getUrl() {
		return url;
	}
}
