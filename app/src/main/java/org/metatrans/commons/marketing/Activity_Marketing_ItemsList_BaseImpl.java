package org.metatrans.commons.marketing;


import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import org.metatrans.commons.Activity_Base;
import org.metatrans.commons.R;
import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.cfg.colours.ConfigurationUtils_Colours;
import org.metatrans.commons.cfg.colours.IConfigurationColours;
import org.metatrans.commons.cfg.publishedapp.IHomeAdInfo;
import org.metatrans.commons.events.api.IEvent_Base;
import org.metatrans.commons.events.api.IEventsManager;
import org.metatrans.commons.ui.list.ListViewFactory;
import org.metatrans.commons.ui.list.RowItem_IdTD;
import org.metatrans.commons.ui.utils.BitmapUtils;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;


public abstract class Activity_Marketing_ItemsList_BaseImpl extends Activity_Base {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		try {
			
			LayoutInflater inflater = LayoutInflater.from(this);

			ViewGroup frame = ListViewFactory.create_ITD_ByXML(this, inflater, buildRows(), new OnItemClickListener_Menu());

			IConfigurationColours coloursCfg = ConfigurationUtils_Colours.getConfigByID(((Application_Base) getApplication()).getUserSettings().uiColoursID);

			int color_background = coloursCfg.getColour_Delimiter();

			frame.setBackgroundColor(color_background);

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
	

	protected abstract boolean isAvailable(IHomeAdInfo item);


	protected abstract List<? extends IHomeAdInfo> getItemsList();


	protected abstract boolean openTarget(IHomeAdInfo promoted);


	public List<RowItem_IdTD> buildRows() throws NotFoundException, SAXException,
			IOException, ParserConfigurationException {
		
		List<RowItem_IdTD> rowItems = new ArrayList<RowItem_IdTD>();
		
		List<? extends IHomeAdInfo> apps = getItemsList();
		
		for (IHomeAdInfo app: apps) {
			
			if (app.getMarketURL() != null) {
				
				Bitmap bitmap = BitmapUtils.fromResource(this, app.getIconResID(), getIconSize());

				String description =
						getString(app.getDescription_Line1())
						+ getDescriptionsDelim()
						+ getString(app.getDescription_Line2());
				
				boolean available = isAvailable(app);
				
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

	
	protected String getDescriptionsDelim() {
		return " ";
	}


	private class OnItemClickListener_Menu implements
			AdapterView.OnItemClickListener {
		
		
		private OnItemClickListener_Menu() {
		}
		
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			
			List<? extends IHomeAdInfo> apps = getItemsList();

			IHomeAdInfo promoted = apps.get((int) id);

			openTarget(promoted);

			
        	//If statement and try-catch statement is for compatibility with CAFK
        	try {
	        	if (Application_Base.getInstance() != null && Application_Base.getInstance().getEventsManager() != null) {
	        		IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
	        		eventsManager.register(Activity_Marketing_ItemsList_BaseImpl.this, eventsManager.create(IEvent_Base.MARKETING, IEvent_Base.MARKETING_APPLIST_ITEM_CLICKED, promoted.getID().hashCode(),
	        			"MARKETING", "APPLIST_ITEM_CLICKED", "" + promoted.getID()));
	        	}
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
		}
	}
}
