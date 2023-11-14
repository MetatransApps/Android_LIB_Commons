package org.metatrans.commons.cfg.sound;


import android.app.Activity;
import android.content.Intent;

import org.metatrans.commons.R;
import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.cfg.menu.Config_MenuMain_Base;
import org.metatrans.commons.menu.Activity_Menu_Sound;


public class Config_MenuMain_Sound extends Config_MenuMain_Base {


        @Override
        public int getName() {
            return R.string.sound;
        }


        @Override
        public int getIconResID() {

            int sound_cfg_id = Application_Base.getInstance().getUserSettings().common_sound_cfg_id;

            if (sound_cfg_id == IConfigurationSound.CFG_SOUND_ON) {

                return ConfigurationUtils_Sound.getConfigByID(IConfigurationSound.CFG_SOUND_ON).getIconResID();

            } else {

                return ConfigurationUtils_Sound.getConfigByID(IConfigurationSound.CFG_SOUND_OFF).getIconResID();
            }
        }


        @Override
        public int getID() {
            return CFG_MENU_SOUND;
        }

        @Override
        public String getDescription_String() {

            int sound_cfg_id = Application_Base.getInstance().getUserSettings().common_sound_cfg_id;

            if (sound_cfg_id == IConfigurationSound.CFG_SOUND_ON) {

                return Application_Base.getInstance().getString(R.string.on);

            } else {

                return Application_Base.getInstance().getString(R.string.silent);
            }
        }

        @Override
        public Runnable getAction() {

            return new Runnable() {

                @Override
                public void run() {

                    Activity currentActivity = Application_Base.getInstance().getCurrentActivity();

                    if (currentActivity != null) {

                        currentActivity.finish();

                        Intent i = new Intent(currentActivity, Activity_Menu_Sound.class);

                        currentActivity.startActivity(i);
                    }
                }
            };
        }

}
