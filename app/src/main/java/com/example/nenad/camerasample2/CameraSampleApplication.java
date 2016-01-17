package com.example.nenad.camerasample2;

import android.app.Application;

import com.example.nenad.camerasample2.networking.APIService;

import javax.inject.Inject;

/**
 * Created by nenad on 12/12/15.
 */
public class CameraSampleApplication extends Application {

    protected static CameraSampleApplication instance;

    @Inject
    protected APIService apiService;

    public APIService getApiService() {
        return apiService;
    }

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
