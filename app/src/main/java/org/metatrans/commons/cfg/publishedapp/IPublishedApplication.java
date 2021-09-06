package org.metatrans.commons.cfg.publishedapp;


import org.metatrans.commons.cfg.appstore.IAppStore;


public interface IPublishedApplication extends IHomeAdInfo {
	
	public String getPackage();
	
	public IAppStore getAppStore();
	
	public boolean isSocial();
	
	public IPublishedApplication getSocialVersion();
	
	public IPublishedApplication getPaidVersion();

	public void setPaidVersion(IPublishedApplication paidVersion);
	
}
