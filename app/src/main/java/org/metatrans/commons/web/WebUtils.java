package org.metatrans.commons.web;


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


	public static final boolean openApplicationStorePage(Context context, IPublishedApplication app) {


		try {

			Uri url = Uri.parse(app.getMarketURL());

			Intent intent = getViewIntent(url);

			//if (intent.resolveActivity(context.getPackageManager()) != null) {

				try {

					context.startActivity(intent);

					return true;

				} catch (Exception ex1) {

					ex1.printStackTrace();
				}
			//}

		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

			String app_home = (new MarketURLGen_OurWebsite(app.getPackage())).getUrl();

			openApplicationWebpage(context, app_home);

			return true;

		} catch (Exception ex1) {

			ex1.printStackTrace();
		}


		return false;
	}


	private static Intent getViewIntent(Uri uri) {

		Intent intent = new Intent(Intent.ACTION_VIEW, uri);

		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

		return intent;
	}
}
