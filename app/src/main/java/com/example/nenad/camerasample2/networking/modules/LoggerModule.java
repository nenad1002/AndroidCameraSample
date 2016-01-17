package com.example.nenad.camerasample2.networking.modules;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import timber.log.Timber;

/**
 * Created by nenad on 17/01/16.
 */
@Module
public class LoggerModule implements RestAdapter.Log {

    @Override
    public void log(String message) {
        Timber.d(message);
    }

    @Provides
    public RestAdapter.Log provideLogger() {
        return this;
    }

    @Provides
    public RestAdapter.LogLevel provideLogLevel() {
        return RestAdapter.LogLevel.FULL;
    }
}
