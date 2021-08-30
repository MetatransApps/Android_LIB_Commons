package org.metatransapps.commons.cfg.app;


public class AppConfig_Base implements IAppConfig {
	
	
	@Override
	public boolean isPaid() {
		return false;
	}
	
	
	@Override
	public String getCompanyWebSiteURL() {
		return "http://metatransapps.com";
	}
	
	
	@Override
	public String getTag_Help() {
		throw new UnsupportedOperationException();
	}
	
	
	@Override
	public String getTag_Description() {
		throw new UnsupportedOperationException();
	}
	
	
	@Override
	public String getTag_RevisionHistory() {
		throw new UnsupportedOperationException();
	}
	
	
	@Override
	public String getTag_ScoresTable() {
		throw new UnsupportedOperationException();
	}
}
