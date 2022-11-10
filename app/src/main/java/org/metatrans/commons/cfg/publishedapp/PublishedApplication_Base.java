package org.metatrans.commons.cfg.publishedapp;


import org.metatrans.commons.cfg.appstore.IAppStore;


public abstract class PublishedApplication_Base implements IPublishedApplication {
	
	
	private IAppStore store;
	private IPublishedApplication socialVersion;
	public IPublishedApplication paidVersion;
	private String app_storeID;
	private IMarketURLGen marketURLGen;
	
	
	public PublishedApplication_Base(IAppStore _store) {
		this(_store, null);
	}
	
	
	public PublishedApplication_Base(IAppStore _store, String _app_storeID) {
		this(_store, null, _app_storeID);
	}
	

	
	protected PublishedApplication_Base(IAppStore _store, IPublishedApplication _socialVersion, String _app_storeID) {
		
		store = _store;
		socialVersion = _socialVersion;
		app_storeID = _app_storeID;
		
		if (isSocial() && socialVersion != null) {
			throw new IllegalStateException("Is social but social version is not null");
		}
		
		if (socialVersion != null) {
			if (!socialVersion.getPackage().startsWith(getPackage())) {
				throw new IllegalStateException("The package of social version " + socialVersion.getPackage()
						+ " do not start with the package of the not social version " + getPackage());
			}
		}
		
		
		if (isPaid() && paidVersion != null) {
			throw new IllegalStateException("Is paid but paid version is not null");
		}
	}
	
	
	@Override
	public Object getID() {
		return getAppStore().getName() + ":" + getPackage();
	}


	@Override
	public String getMarketURL() {
		
		if (marketURLGen == null) {
			marketURLGen = createMarketURLGen();
		}
		
		if (marketURLGen != null) {
			return marketURLGen.getUrl();
		}
		
		return null;
	}
	
	
	private IMarketURLGen createMarketURLGen() {
		
		switch (store.getID()) {
			
			case IAppStore.ID_GOOGLE:
				return new MarketURLGen_Google(getPackage());
				
			case IAppStore.ID_SAMSUNG:
				return new MarketURLGen_Samsung(getPackage());
				
			case IAppStore.ID_AMAZON:
				return new MarketURLGen_Amazon(getPackage());

			case IAppStore.ID_HUAWEI:
				return new MarketURLGen_Huawei(getPackage());

			case IAppStore.ID_FDROID_OFFICIAL:
				return new MarketURLGen_FDroidOfficial(getPackage());

			case IAppStore.ID_FDROID_OWN:
				return new MarketURLGen_FDroidOwn(getPackage());

			default:

				return new MarketURLGen_OurWebsite(getPackage());
		}
	}


	@Override
	public IAppStore getAppStore() {
		return store;
	}
	
	
	@Override
	public boolean isSocial() {
		return false;
	}
	
	
	@Override
	public boolean isPaid() {
		return false;
	}
	
	
	@Override
	public IPublishedApplication getSocialVersion() {
		return socialVersion;
	}
	
	
	@Override
	public IPublishedApplication getPaidVersion() {
		return paidVersion;
	}


	@Override
	public void setPaidVersion(IPublishedApplication paidVersion) {
		this.paidVersion = paidVersion;
	}
}
