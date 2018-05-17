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

        getViewState().showCount(count);
    }

    public void incrementCount() {

        count++;
        Timber.d("Count: " + count);

        getViewState().showCount(count);
    }

    public void onSettingsClick() {

        getViewState().showSettings();
    }
}
