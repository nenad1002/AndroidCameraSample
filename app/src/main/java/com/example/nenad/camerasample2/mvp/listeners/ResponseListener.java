package com.example.nenad.camerasample2.mvp.listeners;

/**
 * Created by nenad on 1/17/16.
 */
public interface ResponseListener<T> {

    void onSuccess(T response);

    void onError(String error);

}