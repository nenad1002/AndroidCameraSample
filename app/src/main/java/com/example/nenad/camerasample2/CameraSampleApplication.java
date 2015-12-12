package com.example.nenad.camerasample2;

import android.app.Application;

/**
 * Created by nenad on 12/12/15.
 */
public class CameraSampleApplication extends Application {

    protected static CameraSampleApplication instance;

    public static CameraSampleApplication getInstance() {
        return instance;
    }

    protected static void setInstance(CameraSampleApplication cameraSampleApplication) {
        instance = cameraSampleApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
    }
}
