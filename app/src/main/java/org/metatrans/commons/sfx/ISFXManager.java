package org.metatrans.commons.sfx;


import android.media.MediaPlayer;


public interface ISFXManager {

    /*
     * Creates MediaPlayer, play a sound and release the player - nothing to be called in addition.
     */
    void playSound(int sound_res_id);

    /*
     * Need to call only start() of MediaPlayer many times as you need.
     * Also mediaPlayer.release() have to be called for the instance when finished with it.
     */
    MediaPlayer createPlayerForSound(int sound_res_id);
}
