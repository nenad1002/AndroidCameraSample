package com.example.nenad.camerasample2.networking.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by nenad on 17/01/16.
 */
public class GsonUtils {

    private GsonUtils() {
    }

    public static Gson getSampleAppGson() {
        return new GsonBuilder().create();
    }
}
