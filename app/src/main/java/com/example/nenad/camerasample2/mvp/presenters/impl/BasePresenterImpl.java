package com.example.nenad.camerasample2.mvp.presenters.impl;

import com.example.nenad.camerasample2.mvp.interactors.BaseInteractor;
import com.example.nenad.camerasample2.mvp.presenters.BasePresenter;

/**
 * Created by nenad on 1/17/16.
 */
public abstract class BasePresenterImpl<V, I> implements BasePresenter {

    private V view;

    private I interactor;

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }

    public I getInteractor() {
        return interactor;
    }

    public void setInteractor(I interactor) {
        this.interactor = interactor;
    }


    @Override
    public void cancel() {
        ((BaseInteractor) interactor).cancel();
    }


}
