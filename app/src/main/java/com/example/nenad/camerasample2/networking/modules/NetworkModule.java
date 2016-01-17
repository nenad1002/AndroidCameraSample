package com.example.nenad.camerasample2.networking.modules;

import com.example.nenad.camerasample2.networking.APIService;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.converter.Converter;

/**
 * Created by nenad on 1/17/16.
 */
@Module(includes = {
        ClientModule.class,
        GsonConverterModule.class,
        HostModule.class,
        LoggerModule.class})
public class NetworkModule {

    @Provides
    public APIService provideAPIService(Client client, Endpoint endpoint, Converter converter,
                                        RestAdapter.Log logger, RestAdapter.LogLevel logLevel) {
        RestAdapter.Builder builder = new RestAdapter.Builder().setClient(client).setEndpoint(endpoint).
                setConverter(converter).setLog(logger).setLogLevel(logLevel);
        RestAdapter restAdapter = builder.build();
        return restAdapter.create(APIService.class);
    }
}
