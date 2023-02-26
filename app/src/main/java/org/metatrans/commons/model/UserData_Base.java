package org.metatrans.commons.model;


import org.metatrans.commons.app.Application_Base;

import java.io.Serializable;


public class UserData_Base implements Serializable {


    private static final long serialVersionUID          = 1953503750930767513L;

    public static final String FILE_NAME_USER_DATA 		= "user_data";

    public static final int MODEL_VERSION_LATEST 		= 1;


    public int model_version = MODEL_VERSION_LATEST;

    public int count_played_games;


    public UserData_Base() {

    }

    public void save() {

        Application_Base.getInstance().storeUserData(this);
    }
}
