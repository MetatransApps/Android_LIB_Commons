package org.metatrans.commons.marketing;


import java.util.List;

import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.cfg.publishedapp.IHomeAdInfo;
import org.metatrans.commons.cfg.publishedapp.IPublishedApplication;
import org.metatrans.commons.cfg.publishedapp.PublishedApplication_Utils;
import org.metatrans.commons.web.WebUtils;


public class Activity_Marketing_AppList extends Activity_Marketing_ItemsList_BaseImpl {
	
	
	@Override
	protected boolean isAvailable(IHomeAdInfo item) {
		return !getPackageName().equals(((IPublishedApplication)item).getPackage());
	}


	@Override
	protected List<? extends IHomeAdInfo> getItemsList() {


		IPublishedApplication app = Application_Base.getInstance().getApp_Me();


		List<IPublishedApplication> apps = null;

		if (app != null && app.isPaid()) {

			apps = PublishedApplication_Utils.getStoreApps_PreferPaid(((Application_Base)getApplication()).getAppStore());

		} else {

			apps = PublishedApplication_Utils.getStoreApps_FreeOnly(((Application_Base)getApplication()).getAppStore());
		}


		return apps;
	}


	@Override
	protected boolean openTarget(IHomeAdInfo promoted) {
		return WebUtils.openApplicationStorePage(this, (IPublishedApplication) promoted);
	}
}
