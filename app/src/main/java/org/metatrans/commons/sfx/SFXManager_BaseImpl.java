package org.metatrans.commons.sfx;


import android.media.AudioAttributes;
import android.media.MediaPlayer;

import org.metatrans.commons.app.Application_Base;


/*
 * In summary, calling MediaPlayer.create() multiple times is generally efficient,
 * but it's important to manage your MediaPlayer instances properly,
 * release them when you're done, and consider reusing instances when it makes sense
 * for your specific use case to avoid resource and performance issues.
 * So, playSound(int sound_res_id) is not caching instances
 * and MediaPlayer createPlayerForSound(int sound_res_id) can be used for caching instances.
 * When use createPlayerForSound and cache its instances, mediaPlayer.release() have to be called for the instances when finished with them.
 */
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


        final MediaPlayer mediaPlayer = createPlayerByResID(sound_res_id);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                mediaPlayer.stop();
                mediaPlayer.reset();

                Application_Base.getInstance().getExecutor().submit(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            Thread.sleep(100);

                        } catch (InterruptedException e) {
                        }

                        mediaPlayer.release();
                    }
                });
            }
        });

        mediaPlayer.start();
    }


    @Override
    public MediaPlayer createPlayerForSound(int sound_res_id) {

        final MediaPlayer mediaPlayer = createPlayerByResID(sound_res_id);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                mediaPlayer.stop();
                mediaPlayer.reset();
            }
        });

        return mediaPlayer;
    }


    private MediaPlayer createPlayerByResID(int sound_res_id) {

        final MediaPlayer mediaPlayer = MediaPlayer.create(Application_Base.getInstance(), sound_res_id);

        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build());

        return mediaPlayer;
    }
}
