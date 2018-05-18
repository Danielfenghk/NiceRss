package com.appsbrook.nicerss.presentation.presenter;

import com.appsbrook.nicerss.presentation.view.MainMvpView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import hugo.weaving.DebugLog;

@DebugLog
@InjectViewState
public class MainMvpPresenter extends MvpPresenter<MainMvpView> {

    public MainMvpPresenter() {
    }

    public void onSettingsClick() {

        getViewState().showSettings();
    }

    public void processAddNewSourceClick() {

        getViewState().showAddNewSourceDialog();
    }
}