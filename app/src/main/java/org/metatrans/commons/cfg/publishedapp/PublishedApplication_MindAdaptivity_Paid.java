package org.metatrans.commons.cfg.publishedapp;


import org.metatrans.commons.R;
import org.metatrans.commons.cfg.appstore.IAppStore;


public class PublishedApplication_MindAdaptivity_Paid extends PublishedApplication_Base_Paid {


	public PublishedApplication_MindAdaptivity_Paid(IAppStore _store) {
		super(_store);
	}


	public PublishedApplication_MindAdaptivity_Paid(IAppStore _store, String _app_storeID) {
		super(_store, _app_storeID);
	}
	
	
	@Override
	public String getPackage() {
		return "mat.cards.paid";
	}
	
	
	@Override
	public int getName() {
		return R.string.app_mind_adaptivity_name;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_logo_wcst;
	}
	
	
	@Override
	public int getDescription_Line1() {
		return R.string.app_mind_adaptivity_advertising1;
	}
	
	
	@Override
	public int getDescription_Line2() {
		return R.string.app_mind_adaptivity_advertising2;
	}
}
