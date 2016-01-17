package com.example.nenad.camerasample2.networking.components;

import com.example.nenad.camerasample2.CameraSampleApplication;
import com.example.nenad.camerasample2.networking.modules.NetworkModule;

import dagger.Component;

/**
 * Created by nenad on 1/17/16.
 */


@Component(modules = NetworkModule.class)
public interface NetworkComponent {

    void inject(CameraSampleApplication application);
}
