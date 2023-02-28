package org.metatrans.commons.model;


import org.metatrans.commons.app.Application_Base;

import java.io.Serializable;


public class UserData_Base implements Serializable {


    private static final long serialVersionUID          = 1953503750930767513L;

    public static final String FILE_NAME_USER_DATA 		= "user_data";

    public static final int MODEL_VERSION_LATEST 		= 1;

    private static final int INITIAL_COUNT_USAGE_EVENTS_BETWEEN_SHOWING_DIALOG = 7;


    private int model_version = MODEL_VERSION_LATEST;

    private int count_usage_events;

    private int count_apprating_dialog_shown;

    private int count_last_shown_apprating_dialog_on_usage_events;

    protected int count_usage_events_between_showing_apprating_dialog;


    public UserData_Base() {

        count_usage_events_between_showing_apprating_dialog = INITIAL_COUNT_USAGE_EVENTS_BETWEEN_SHOWING_DIALOG;
    }


    public void save() {

        Application_Base.getInstance().storeUserData(this);
    }


    public int getUsageEventsCount() {

        return count_usage_events;
    }


    public void incUsageEventsCount() {

        count_usage_events++;

        save();

        System.out.println("UserData_Base.incUsageEventsCount(): count_usage_events=" + count_usage_events);
    }


    public int getModelVersion() {

        return model_version;
    }


    public boolean showAppRatingDialog() {

        return count_last_shown_apprating_dialog_on_usage_events + count_usage_events_between_showing_apprating_dialog <= count_usage_events;
    }


    public int getCountAppRatingDialogShown() {

        return count_apprating_dialog_shown;
    }


    public void appRatingDialogShown() {

        count_apprating_dialog_shown++;

        count_last_shown_apprating_dialog_on_usage_events = count_usage_events;

        count_usage_events_between_showing_apprating_dialog = Math.max(
                count_usage_events_between_showing_apprating_dialog + 1,
                INITIAL_COUNT_USAGE_EVENTS_BETWEEN_SHOWING_DIALOG);

        save();
    }
}
