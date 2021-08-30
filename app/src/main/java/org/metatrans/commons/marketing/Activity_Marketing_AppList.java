package org.metatrans.commons.marketing;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.metatrans.commons.Activity_Base;
import org.metatrans.commons.R;
import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.cfg.publishedapp.IPublishedApplication;
import org.metatrans.commons.cfg.publishedapp.PublishedApplication_Utils;
import org.metatrans.commons.events.api.IEvent_Base;
import org.metatrans.commons.events.api.IEventsManager;
import org.metatrans.commons.ui.images.IBitmapCache;
import org.metatrans.commons.ui.list.ListViewFactory;
import org.metatrans.commons.ui.list.RowItem_IdTD;
import org.metatrans.commons.ui.utils.BitmapUtils;
import org.metatrans.commons.web.WebUtils;
import org.xml.sax.SAXException;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;


public class Activity_Marketing_AppList extends Activity_Base {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		try {
			
			LayoutInflater inflater = LayoutInflater.from(this);
			ViewGroup frame = ListViewFactory.create_ITD_ByXML(this, inflater, buildRows(), new OnItemClickListener_Menu());
			
			setContentView(frame);
			
			setBackgroundPoster(R.id.commons_listview_frame, 55);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public void onPause() {
		
		System.out.println("Activity_Marketing_AppList: onPause()");
		
		super.onPause();
		
	}
	
	
	@Override
	public void onResume() {
		
		System.out.println("Activity_Marketing_AppList: onResume()");
		
		super.onResume();
		
	}
	
	
	public List<RowItem_IdTD> buildRows() throws NotFoundException, SAXException,
			IOException, ParserConfigurationException {
		
		List<RowItem_IdTD> rowItems = new ArrayList<RowItem_IdTD>();
		
		List<IPublishedApplication> apps = getAppsList();
		
		for (IPublishedApplication app: apps) {
			
			if (app.getMarketURL() != null) {
				
				Bitmap bitmap = BitmapUtils.fromResource(this, app.getIconResID(), getIconSize());
				String description = getString(app.getDescription_Line1()) + " " + getString(app.getDescription_Line2());
				
				boolean available = !getPackageName().equals(app.getPackage());
				
				/*
				//HACK for not showing the free version in paid app menu
				if (Application_Base.getInstance().getAppConfig().isPaid()) {
					available = !getPackageName().startsWith(app.getPackage());
				}
				//HACK
				*/
				
				if (!available) {
					
					Bitmap bitmap_org = bitmap;
					bitmap = BitmapUtils.toGrayscale(bitmap);
					BitmapUtils.recycle(bitmap, bitmap_org);
					
					description = getString(R.string.label_installed).toUpperCase();
				}
				
				Drawable drawable = BitmapUtils.createDrawable(this, bitmap);
				
				RowItem_IdTD item = new RowItem_IdTD(available, drawable, getString(app.getName()), description);
				
				rowItems.add(item);
			}
		}
		
		return rowItems;
	}
	
	
	private List<IPublishedApplication> getAppsList() {
		
		List<IPublishedApplication> apps = null;
		
		if (Application_Base.getInstance().getAppConfig().isPaid()) {
			apps = PublishedApplication_Utils.getStoreApps_PreferPaid(((Application_Base)getApplication()).getAppStore());
		} else {
			apps = PublishedApplication_Utils.getStoreApps_FreeOnly(((Application_Base)getApplication()).getAppStore());
		}
		
		return apps;
	}
	
	
	private int getResourceIDByName(String name) {
		String packageName = getPackageName();
		// System.out.println("packageName=" + packageName);
		int resId = getResources().getIdentifier(name, null, packageName);
		return resId;
	}
	
	
	private class OnItemClickListener_Menu implements
			AdapterView.OnItemClickListener {
		
		
		private OnItemClickListener_Menu() {
		}
		
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			
			List<IPublishedApplication> apps = getAppsList();
			
			IPublishedApplication app = apps.get((int) id);
			
			WebUtils.openApplicationStorePage(Activity_Marketing_AppList.this, app);
			
        	//If statement and try-catch statement is for compatibility with CAFK
        	try {
	        	if (Application_Base.getInstance() != null && Application_Base.getInstance().getEventsManager() != null) {
	        		IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
	        		eventsManager.register(Activity_Marketing_AppList.this, eventsManager.create(IEvent_Base.MARKETING, IEvent_Base.MARKETING_APPLIST_ITEM_CLICKED, app.getID().hashCode(),
	        			"MARKETING", "APPLIST_ITEM_CLICKED", "" + app.getID()));
	        	}
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
		}
	}
	
	
	@Override
	protected int getBackgroundImageID() {
		return 0;//R.drawable.ic_colours_tube;
	}
	
	
	@Override
	protected IBitmapCache getBitmapCache() {
		
		return new IBitmapCache() {
			
			@Override
			public void remove(Integer id) {
			}
			
			
			@Override
			public Bitmap getBitmap(Context context, int imageID) {
				return BitmapUtils.fromResource(Activity_Marketing_AppList.this, imageID);
			}
			
			
			@Override
			public void addBitmap(int imageID, Bitmap bitmap) {
			}
		};
	}
}
