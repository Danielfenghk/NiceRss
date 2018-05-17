package com.appsbrook.nicerss.mvp.presenters;

import com.appsbrook.nicerss.mvp.views.MainMvpView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import hugo.weaving.DebugLog;
import timber.log.Timber;

@DebugLog
@InjectViewState
public class MainMvpPresenter extends MvpPresenter<MainMvpView> {

    int count = 0;

    public MainMvpPresenter() {

    }


    public void onSettingsClick() {

        getViewState().showSettings();
    }
}
