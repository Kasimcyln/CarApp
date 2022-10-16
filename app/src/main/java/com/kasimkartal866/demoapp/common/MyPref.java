package com.kasimkartal866.demoapp.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.kasimkartal866.demoapp.orm.User;

public class MyPref {
    private static MyPref instance;
    Context context;
    private static final String PREF_USER_NAME  ="username";
    private static final String PREF_USER_ID  ="userid";
    private SharedPreferences pref;

    private SharedPreferences.Editor editor;

    private MyPref () {
        context  = App.getContext();
        pref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        editor = pref.edit();
    }
    public static MyPref getInstance() {
        if(instance == null) {
            instance = new MyPref();
        }
        return instance;
    }
    //.
    public void saveUserName (String userName) {
        editor.putString(PREF_USER_NAME,userName).apply();
    }

    public String getUserName () {
        return pref.getString(PREF_USER_NAME,"");
    }
    public int getUserId () {
        return pref.getInt(PREF_USER_ID, -1);
    }
    public void clearUserData () {
        editor.putString(PREF_USER_NAME,"").apply();
        editor.putInt(PREF_USER_ID,-1).apply();
    }

    public void saveUserInfo(User user) {
        editor.putString(PREF_USER_NAME,user.getEmail()).apply();
        editor.putInt(PREF_USER_ID,user.getId()).apply();
    }
}
