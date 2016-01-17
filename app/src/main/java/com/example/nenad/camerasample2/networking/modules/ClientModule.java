package com.example.nenad.camerasample2.networking.modules;

import com.squareup.okhttp.OkHttpClient;

import dagger.Module;
import dagger.Provides;
import retrofit.client.Client;
import retrofit.client.OkClient;

/**
 * Created by nenad on 17/01/16.
 */
@Module
public class ClientModule {

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient client = new OkHttpClient();
        return client;
    }

    @Provides
    public Client provideClient() {
        return new OkClient(getOkHttpClient());
    }
}
