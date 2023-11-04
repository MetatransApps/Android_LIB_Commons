package org.metatrans.commons.melody;


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
import org.metatrans.commons.cfg.melody.ConfigurationUtils_Melody;
import org.metatrans.commons.cfg.melody.IConfigurationMelody;
import org.metatrans.commons.events.api.IEvent_Base;
import org.metatrans.commons.events.api.IEventsManager;
import org.metatrans.commons.model.UserSettings_Base;
import org.metatrans.commons.ui.list.ListViewFactory;
import org.metatrans.commons.ui.list.RowItem_CIdTD;
import org.metatrans.commons.ui.utils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

public class Activity_Menu_Melody extends Activity_Base {


    @Override
    public void onCreate(Bundle savedInstanceState) {


        System.out.println("Activity_Menu_Melody: onCreate()");

        super.onCreate(savedInstanceState);


        int melody_cfg_id = ((UserSettings_Base) Application_Base.getInstance().getUserSettings()).melody_cfg_id;

        int currOrderNumber = ConfigurationUtils_Melody.getOrderNumber(melody_cfg_id);

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

        IConfigurationMelody[] sound_cfgs = ConfigurationUtils_Melody.getAll();

        for (int i = 0; i < sound_cfgs.length; i++) {

            IConfigurationMelody melody_cfg = sound_cfgs[i];

            Bitmap bitmap = BitmapUtils.fromResource(this, melody_cfg.getIconResID(), getIconSize());

            Bitmap old = bitmap;

            bitmap = BitmapUtils.createScaledBitmap(bitmap, getIconSize(), getIconSize());
            BitmapUtils.recycle(bitmap, old);
            Drawable drawable = BitmapUtils.createDrawable(this, bitmap);

            RowItem_CIdTD item = new RowItem_CIdTD(i == initialSelection, drawable, getString(melody_cfg.getName()), "");

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

            //System.out.println("ColoursSelection POS=" + position + ", id=" + id);

            Application_Base.getInstance().getSFXManager().playSound(R.raw.sfx_button_pressed_2);

            int melody_cfg_id = ((UserSettings_Base)Application_Base.getInstance().getUserSettings()).melody_cfg_id;

            int currOrderNumber = ConfigurationUtils_Melody.getOrderNumber(melody_cfg_id);

            //IConfigurationSound sound_sfg = ConfigurationUtils_Sound.getConfigByID(sound_cfg_id);

            if (position != currOrderNumber) {

                int newCfgID = ConfigurationUtils_Melody.getID(position);

                changeSound(newCfgID);
            }

            finish();
        }
    }


    public void changeSound(int new_melody_cfg_id) {

        ((UserSettings_Base)Application_Base.getInstance().getUserSettings()).melody_cfg_id = new_melody_cfg_id;

        ((Application_Base)getApplication()).storeUserSettings();

        Application_Base.getInstance().getMelodiesManager().setMelody(new_melody_cfg_id);

        IConfigurationMelody cfg_melody = ConfigurationUtils_Melody.getConfigByID(new_melody_cfg_id);

        IEventsManager eventsManager = Application_Base.getInstance().getEventsManager();
        eventsManager.register(this,
                IEvent_Base.EVENT_MENU_OPERATION_CHANGE_MELODY.createByVarianceInCategory3(
                        new_melody_cfg_id, getString(cfg_melody.getName())
                )
        );
    }
}
