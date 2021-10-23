package org.metatrans.commons.cfg.publishedapp;


import org.metatrans.commons.R;
import org.metatrans.commons.cfg.appstore.IAppStore;


public class PublishedApplication_Words_DE_for_BG extends PublishedApplication_Base {


	public PublishedApplication_Words_DE_for_BG(IAppStore _store) {
		super(_store);
	}
	
	
	@Override
	public String getPackage() {
		return "com.words.exercise.german_for_bulgarians";
	}
	
	
	@Override
	public int getName() {
		return R.string.app_words_de_for_bg_name;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_logo_words_de_for_bg;
	}
	
	
	@Override
	public int getDescription_Line1() {
		return R.string.app_words_de_for_bg_advertising1;
	}
	
	
	@Override
	public int getDescription_Line2() {
		return R.string.app_words_de_for_bg_advertising2;
	}
}
