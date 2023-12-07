package org.metatrans.commons.sfx;


import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.cfg.sound.IConfigurationSound;

import java.util.HashMap;
import java.util.Map;


/*
 *
 */
public class SFXManager_SoundPoolImpl implements ISFXManager {


    private static SFXManager_SoundPoolImpl singleton;

    private static Map<Integer, Integer> RES2ID = new HashMap<Integer, Integer>();

    private static SoundPool SOUND_POOL;

    static {

        // For Android Lollipop and above
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        SOUND_POOL = new SoundPool.Builder()
                .setMaxStreams(30)
                .setAudioAttributes(attributes)
                .build();
    }

    public static ISFXManager getSingleton() {

        if (singleton == null) {

            singleton = new SFXManager_SoundPoolImpl();
        }

        return singleton;
    }


    private SFXManager_SoundPoolImpl() {


    }


    @Override
    public void loadSounds(Context context, int[] sound_res_ids) {

        for (int i = 0; i < sound_res_ids.length; i++) {

            int sound_res_id = sound_res_ids[i];

            Integer id_obj = RES2ID.get(sound_res_id);

            if (id_obj != null) {

                throw new IllegalStateException("Duplicated sound sound_res_id=" + sound_res_id);
            }

            id_obj = SOUND_POOL.load(context, sound_res_id, 1);

            RES2ID.put(sound_res_id, id_obj);
        }
    }


    @Override
    public int playSound(int sound_res_id) {

        return playSound(sound_res_id, 0);
    }


    @Override
    public int playSound_Repeat(int sound_res_id) {

        return playSound(sound_res_id, -1);
    }


    @Override
    public void stopSound(int stream_id) {

        SOUND_POOL.stop(stream_id);
    }


    public int playSound(int sound_res_id, int loop) {

        //-1 is just marking places in code, where the sfx should be played
        /*if (sound_res_id == -1) {

            return;
        }*/

        if (Application_Base.getInstance().getUserSettings().common_sound_cfg_id == IConfigurationSound.CFG_SOUND_OFF) {

            return 0;
        }


        Integer id_obj = RES2ID.get(sound_res_id);

        if (id_obj == null) {

            throw new IllegalStateException("Sound not found sound_res_id=" + sound_res_id);
        }

        int stream_id = SOUND_POOL.play(id_obj, 1.0f, 1.0f, 1, loop, 1.0f);

        return stream_id;
    }
}
