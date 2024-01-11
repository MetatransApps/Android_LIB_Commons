package org.metatrans.commons.menu;


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
import org.metatrans.commons.cfg.sound.ConfigurationUtils_Sound;
import org.metatrans.commons.cfg.sound.IConfigurationSound;
import org.metatrans.commons.events.api.IEvent_Base;
import org.metatrans.commons.events.api.IEventsManager;
import org.metatrans.commons.ui.list.ListViewFactory;
import org.metatrans.commons.ui.list.RowItem_CIdTD;
import org.metatrans.commons.ui.utils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;


public class Activity_Menu_Sound extends Activity_Base {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		

		System.out.println("Activity_Menu_Sound: onCreate()");
		
		super.onCreate(savedInstanceState);


		int sound_cfg_id = Application_Base.getInstance().getUserSettings().common_sound_cfg_id;

		int currOrderNumber = ConfigurationUtils_Sound.getOrderNumber(sound_cfg_id);

		IConfigurationColours coloursCfg = ConfigurationUtils_Colours.getConfigByID(((Application_Base) getApplication()).getUserSettings().uiColoursID);

		int color_background = coloursCfg.getColour_Background();

		LayoutInflater inflater = LayoutInflater.from(this);

		ViewGroup frame = ListViewFactory.create_CITD_ByXML(this, inflater, buildRows(currOrderNumber), color_background, currOrderNumber, new OnItemClickListener_Menu());
		
		setContentView(frame);
		
		setBackgroundPoster(R.id.commons_listview_frame, 55);
	}
	
	
	@Override
	protected int getBackgroundImageID() {
		return 0;//R.drawable.ic_rainbow;
	}
	
	
	public List<RowItem_CIdTD> buildRows(int initialSelection) {
		
		List<RowItem_CIdTD> rowItems = new ArrayList<RowItem_CIdTD>();

		IConfigurationSound[] sound_cfgs = ConfigurationUtils_Sound.getAll();
		
		for (int i = 0; i < sound_cfgs.length; i++) {

			IConfigurationSound sound_cfg = sound_cfgs[i];

			Bitmap bitmap = BitmapUtils.fromResource(this, sound_cfg.getIconResID(), getIconSize());

			Bitmap old = bitmap;

			bitmap = BitmapUtils.createScaledBitmap(bitmap, getIconSize(), getIconSize());
			BitmapUtils.recycle(bitmap, old);
			Drawable drawable = BitmapUtils.createDrawable(this, bitmap);
			
			RowItem_CIdTD item = new RowItem_CIdTD(i == initialSelection, drawable, getString(sound_cfg.getName()), "");

			rowItems.add(item);
		}
		
		return rowItems;
	}
	
	
	private class OnItemClickListener_Menu implements
			AdapterView.OnItemClickListener {
		
		
		private OnItemClickListener_Menu() {
		}
		
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			Application_Base.getInstance().getSFXManager().playSound(R.raw.sfx_button_pressed_2);

			//System.out.println("ColoursSelection POS=" + position + ", id=" + id);

			int sound_cfg_id = Application_Base.getInstance().getUserSettings().common_sound_cfg_id;

			int currOrderNumber = ConfigurationUtils_Sound.getOrderNumber(sound_cfg_id);

			//IConfigurationSound sound_sfg = ConfigurationUtils_Sound.getConfigByID(sound_cfg_id);

			if (position != currOrderNumber) {

				int newCfgID = ConfigurationUtils_Sound.getID(position);

				changeSound(newCfgID);
			}
			
			finish();
		}
	}
	
	
	public void changeSound(int new_sound_cfg_id) {

		Application_Base.getInstance().getUserSettings().common_sound_cfg_id = new_sound_cfg_id;
		
		((Application_Base)getApplication()).storeUserSettings();

		IConfigurationSound cfg_sound = ConfigurationUtils_Sound.getConfigByID(new_sound_cfg_id);

		IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
		eventsManager.register(this,
				IEvent_Base.EVENT_MENU_OPERATION_CHANGE_SOUND.createByVarianceInCategory3(
						new_sound_cfg_id, getString(cfg_sound.getName())
				)
		);
	}
}
