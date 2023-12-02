package org.metatrans.commons.sfx;


import android.content.Context;

public interface ISFXManager {

    //Load before play
    void loadSounds(Context context, int[] sound_res_ids);

    //Returns stream_id
    int playSound(int sound_res_id);

    //Returns stream_id
    int playSound_Repeat(int sound_res_id);

    void stopSound(int stream_id);
}
