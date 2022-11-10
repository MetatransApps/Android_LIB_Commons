package org.metatrans.commons.cfg.publishedapp;


public class MarketURLGen_FDroidOfficial implements IMarketURLGen {


	private String url;


	public MarketURLGen_FDroidOfficial(String _appID) {

		//The apps must be available on F-DROID OFFICIAL.
		//Currently only Gravity game is available.
		if (_appID.startsWith("com.gravityplay")) {

			url = "https://f-droid.org/packages/" + _appID + "/";

		} else {

			url = (new MarketURLGen_FDroidOwn(_appID)).getUrl();
		}
	}
	
	
	@Override
	public String getUrl() {
		return url;
	}
}
