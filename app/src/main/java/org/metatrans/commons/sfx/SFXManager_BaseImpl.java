package org.metatrans.commons.sfx;


import android.media.AudioAttributes;
import android.media.MediaPlayer;

import org.metatrans.commons.app.Application_Base;


public class SFXManager_BaseImpl implements ISFXManager {


    private static SFXManager_BaseImpl singleton;


    public static ISFXManager getSingleton() {

        if (singleton == null) {

            singleton = new SFXManager_BaseImpl();
        }

        return singleton;
    }


    private SFXManager_BaseImpl() {

    }


    @Override
    public void playSound(int sound_res_id) {

        if (true) {

            return;
        }

        final MediaPlayer mediaPlayer = MediaPlayer.create(Application_Base.getInstance(), sound_res_id);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                mediaPlayer.stop();
                mediaPlayer.reset();

                Application_Base.getInstance().getExecutor().submit(new Runnable() {
                    @Override
                    public void run() {

                        mediaPlayer.release();
                    }
                });
            }
        });

        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build());

        mediaPlayer.start();
    }
}
