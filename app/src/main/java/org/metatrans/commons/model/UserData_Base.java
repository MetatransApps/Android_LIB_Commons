package org.metatrans.commons.model;


import org.metatrans.commons.app.Application_Base;

import java.io.Serializable;


public class UserData_Base implements Serializable {


    private static final long serialVersionUID          = 1953503750930767513L;

    public static final String FILE_NAME_USER_DATA 		= "user_data";

    public static final int MODEL_VERSION_LATEST 		= 1;

    public static final int INITIAL_COUNT_GAMES_BETWEEN_SHOWING_DIALOG = 11;


    private int model_version = MODEL_VERSION_LATEST;

    private int count_played_games;

    private int count_last_shown_apprating_dialog_on_games;

    protected int count_games_between_showing_apprating_dialog;


    public UserData_Base() {

        count_games_between_showing_apprating_dialog = INITIAL_COUNT_GAMES_BETWEEN_SHOWING_DIALOG;
    }


    public void save() {

        Application_Base.getInstance().storeUserData(this);
    }


    public int getPlayedGamesCount() {

        return count_played_games;
    }


    public void incPlayedGamesCount() {

        count_played_games++;

        save();
    }


    public int getModelVersion() {

        return model_version;
    }


    public boolean showAppRatingDialog() {

        return count_last_shown_apprating_dialog_on_games + count_games_between_showing_apprating_dialog < count_played_games;
    }


    public void appRatingDialogShown() {

        count_last_shown_apprating_dialog_on_games = count_played_games;

        count_games_between_showing_apprating_dialog = Math.max(
                count_games_between_showing_apprating_dialog + 1,
                INITIAL_COUNT_GAMES_BETWEEN_SHOWING_DIALOG);

        save();
    }
}
