package com.appsbrook.timister.ui.main;

import timber.log.Timber;

class MainMvpPresenter implements MainContract.MvpPresenter {

    private final MainContract.MvpView mvpView;

    public MainMvpPresenter(MainContract.MvpView mvpView) {
        Timber.d("MainMvpPresenter");

        this.mvpView = mvpView;
    }

    @Override
    public void start() {
        Timber.d("start");

    }

    @Override
    public void processFabClick() {

        mvpView.showFabSnackBar();
    }
}
