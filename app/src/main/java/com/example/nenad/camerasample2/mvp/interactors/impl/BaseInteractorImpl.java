package com.example.nenad.camerasample2.mvp.interactors.impl;

import com.example.nenad.camerasample2.mvp.interactors.BaseInteractor;
import com.example.nenad.camerasample2.mvp.listeners.ResponseListener;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by nenad on 1/17/16.
 */
public abstract class BaseInteractorImpl<T> implements BaseInteractor, Callback<T> {

    private ResponseListener<T> listener;

    protected boolean isCanceled = false;

    @Override
    public void cancel() {
        isCanceled = true;

    }

    @Override
    public void reset() {
        isCanceled = false;

    }

    @Override
    public void success(T t, Response response) {
        if (!isCanceled) {
            listener.onSuccess(t);
        }

    }

    @Override
    public void failure(RetrofitError error) {
        if (!isCanceled) {
            listener.onError(error.toString());
        }

    }

    protected void setListener(ResponseListener<T> listener) {
        this.listener = listener;
    }
}
