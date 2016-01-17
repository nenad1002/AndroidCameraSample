package com.example.nenad.camerasample2.mvp.views;

/**
 * Created by nenad on 1/17/16.
 */
public interface BaseView {

    void showLoader();

    void hideLoader();

    void showErrorMessage(String message);

}
