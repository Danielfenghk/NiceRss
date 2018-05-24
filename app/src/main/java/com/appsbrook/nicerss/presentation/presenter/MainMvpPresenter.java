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

    public void openSettings() {
        getViewState().openSettingsActivity();
    }

    public void processAddNewSourceClick() {
        getViewState().openAddNewSourceDialog();
    }

    public void openRssSources() {
        getViewState().openRssSourcesFragment();
    }

    public void openRssItems() {
        getViewState().openRssItemsFragment();
    }

    public void openFavorites() {
        getViewState().openFavoritesFragment();
    }

    public void openAbout() {
        getViewState().openAboutActivity();
    }
}
