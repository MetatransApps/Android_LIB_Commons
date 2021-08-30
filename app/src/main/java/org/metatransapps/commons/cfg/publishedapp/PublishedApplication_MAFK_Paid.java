package org.metatransapps.commons.cfg.publishedapp;


import org.metatransapps.commons.R;
import org.metatransapps.commons.cfg.appstore.IAppStore;


public class PublishedApplication_MAFK_Paid extends PublishedApplication_Base_Paid {
	
	
	public PublishedApplication_MAFK_Paid(IAppStore _store) {
		super(_store);
	}
	
	
	public PublishedApplication_MAFK_Paid(IAppStore _store, String _app_storeID) {
		super(_store, _app_storeID);
	}
	
	
	@Override
	public String getPackage() {
		return "com.mathforkids5.paid";
	}
	
	
	@Override
	public int getName() {
		return R.string.app_mafk_name_paid;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_logo_mafk;
	}
	
	
	@Override
	public int getDescription_Line1() {
		return R.string.app_mafk_advertising1;
	}
	
	
	@Override
	public int getDescription_Line2() {
		return R.string.app_mafk_advertising2;
	}
}
