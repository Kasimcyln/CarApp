package com.kasimkartal866.demoapp.common;

import android.app.Application;
import android.content.Context;

import com.kasimkartal866.demoapp.orm.RoomExecutor;
import com.kasimkartal866.demoapp.orm.UserDao;
import com.kasimkartal866.demoapp.orm.UserDatabase;

public class App extends Application {
    public static UserDao dao;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        dao = UserDatabase.getUserDatabase(context).userDao();
    }
    public static Context getContext () {
        return context;
    }
    public static MyPref getPref () {
        return MyPref.getInstance();
    }
    public static RoomExecutor getRoomExecutor () {
        return RoomExecutor.getInstance(context);
    }
}
