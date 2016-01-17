package com.example.nenad.camerasample2.networking.modules;

import com.example.nenad.camerasample2.BuildConfig;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;

/**
 * Created by nenad on 17/01/16.
 */
@Module
public class HostModule {

    @Provides
    public Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(BuildConfig.API_URL);
    }
}
