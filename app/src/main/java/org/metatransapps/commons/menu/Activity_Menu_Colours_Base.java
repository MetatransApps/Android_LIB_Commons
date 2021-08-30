package org.metatransapps.commons.menu;


import java.util.ArrayList;
import java.util.List;

import org.metatransapps.commons.Activity_Base;
import org.metatransapps.commons.R;
import org.metatransapps.commons.app.Application_Base;
import org.metatransapps.commons.cfg.colours.ConfigurationUtils_Colours;
import org.metatransapps.commons.cfg.colours.IConfigurationColours;
import org.metatransapps.commons.events.api.IEvent_Base;
import org.metatransapps.commons.events.api.IEventsManager;
import org.metatransapps.commons.ui.list.ListViewFactory;
import org.metatransapps.commons.ui.list.RowItem_CIdTD;
import org.metatransapps.commons.ui.utils.BitmapUtils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;


public class Activity_Menu_Colours_Base extends Activity_Base {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		System.out.println("Activity_Menu_Colours_Base: onCreate()");
		
		super.onCreate(savedInstanceState);
		
		int currOrderNumber = ConfigurationUtils_Colours.getOrderNumber(((Application_Base)getApplication()).getUserSettings().uiColoursID);
		
		LayoutInflater inflater = LayoutInflater.from(this);
		ViewGroup frame = ListViewFactory.create_CITD_ByXML(this, inflater, buildRows(currOrderNumber), currOrderNumber, new OnItemClickListener_Menu());
		
		setContentView(frame);
		
		setBackgroundPoster(R.id.commons_listview_frame, 55);
	}
	
	
	@Override
	protected int getBackgroundImageID() {
		return 0;//R.drawable.ic_rainbow;
	}
	
	
	public List<RowItem_CIdTD> buildRows(int initialSelection) {
		
		List<RowItem_CIdTD> rowItems = new ArrayList<RowItem_CIdTD>();
		
		IConfigurationColours[] coloursCfg = ConfigurationUtils_Colours.getAll();
		
		for (int i = 0; i < coloursCfg.length; i++) {

			IConfigurationColours colourCfg = coloursCfg[i];
			
			Bitmap bitmap = createColourBitmap(colourCfg);
			Bitmap old = bitmap;
			bitmap = BitmapUtils.createScaledBitmap(bitmap, getIconSize(), getIconSize(), false);
			BitmapUtils.recycle(bitmap, old);
			Drawable drawable = BitmapUtils.createDrawable(this, bitmap);
			
			RowItem_CIdTD item = new RowItem_CIdTD(i == initialSelection, drawable, getString(colourCfg.getName()), "");

			rowItems.add(item);
		}
		
		return rowItems;
	}
	
	
	protected Bitmap createColourBitmap(IConfigurationColours colourCfg) {
		
		Bitmap bitmap = BitmapUtils.createLineFrom4Colours(
					getIconSize() / 2, getIconSize(),
					colourCfg.getColour_Square_White(),
					colourCfg.getColour_Square_Black(),
					colourCfg.getColour_Delimiter(),
					colourCfg.getColour_Background()
					);
		
		return bitmap;
	}
	
	
	private class OnItemClickListener_Menu implements
			AdapterView.OnItemClickListener {
		
		
		private OnItemClickListener_Menu() {
		}
		
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			
			//System.out.println("ColoursSelection POS=" + position + ", id=" + id);
			
			int currOrderNumber = ConfigurationUtils_Colours.getOrderNumber(((Application_Base)getApplication()).getUserSettings().uiColoursID);
			if (position != currOrderNumber) {
				int newCfgID = ConfigurationUtils_Colours.getID(position);
				changeColours(newCfgID);
			}
			
			finish();
		}
	}
	
	
	public void changeColours(int uiColoursCfgID) {
		
		((Application_Base)getApplication()).getUserSettings().uiColoursID = uiColoursCfgID;
		
		((Application_Base)getApplication()).storeUserSettings();
		
		IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
		eventsManager.register(this, eventsManager.create(IEvent_Base.MENU_OPERATION, IEvent_Base.MENU_OPERATION_CHANGE_COLOURS, uiColoursCfgID,
				"MENU_OPERATION", "CHANGE_COLOURS", "" + uiColoursCfgID));
	}
}
