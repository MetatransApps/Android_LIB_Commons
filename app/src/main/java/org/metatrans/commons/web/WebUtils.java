package org.metatrans.commons.web;


import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.cfg.publishedapp.IPublishedApplication;
import org.metatrans.commons.cfg.publishedapp.MarketURLGen_OurWebsite;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;


public class WebUtils {


	public static final boolean openApplicationWebpage(Context context, String uriString) {

		Uri url = Uri.parse(uriString);

		Intent intent = getViewIntent(url);

		context.startActivity(intent);

		return true;
	}


	public static final void openApplicationStorePage(Context context, IPublishedApplication app) {


		try {

			Uri url = Uri.parse(app.getMarketURL());

			Intent intent = getViewIntent(url);

			try {

				context.startActivity(intent);

				return;

			} catch (Exception ex1) {

				ex1.printStackTrace();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}


		//If app store page is not opened, we open the app website page
		try {

			//Prevent opening browser for child directed app, because the browser could be used to surf in internet.
			if (!Application_Base.getInstance().isChildDirected()) {

				String app_home = (new MarketURLGen_OurWebsite(app.getPackage())).getUrl();

				openApplicationWebpage(context, app_home);

			} else {

				//Do nothing
			}

		} catch (Exception ex1) {

			ex1.printStackTrace();
		}
	}


	private static Intent getViewIntent(Uri uri) {

		Intent intent = new Intent(Intent.ACTION_VIEW, uri);

		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

		return intent;
	}
}
