package com.example.nenad.camerasample2.networking.modules;

import com.example.nenad.camerasample2.networking.utilities.GsonUtils;

import dagger.Module;
import dagger.Provides;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

/**
 * Created by nenad on 17/01/16.
 */
@Module
public class GsonConverterModule {

    @Provides
    public Converter provideConverter() {
        return new GsonConverter(GsonUtils.getSampleAppGson());
    }

}
