package org.metatrans.commons.cfg.publishedapp;


public class MarketURLGen_FDroidOfficial implements IMarketURLGen {


	private String url;


	public MarketURLGen_FDroidOfficial(String _appID) {

		//The apps must be available on F-DROID OFFICIAL.
		//Currently 3 games is available.
		if (_appID.startsWith("com.gravityplay")
			|| _appID.startsWith("com.stoptheballs")
			|| _appID.startsWith("com.bagaturchess")) {

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
