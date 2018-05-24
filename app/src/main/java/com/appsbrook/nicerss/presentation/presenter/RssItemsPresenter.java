package com.appsbrook.nicerss.presentation.presenter;


import com.appsbrook.nicerss.models.RssItem;
import com.appsbrook.nicerss.interactors.RssItemsLoaderInteractor;
import com.appsbrook.nicerss.presentation.view.RssItemsView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import hugo.weaving.DebugLog;

@DebugLog
@InjectViewState
public class RssItemsPresenter extends MvpPresenter<RssItemsView>
        implements IRssItemsPresenter {

    private RssItemsLoaderInteractor interactor;

    public RssItemsPresenter() {

        interactor = new RssItemsLoaderInteractor(this);
    }

    public void loadNewsItems() {

        interactor.loadNewsItems();
    }

    @Override
    public void onLoadSuccess(List<RssItem> items) {

        getViewState().addNewsItems(items);
        getViewState().hideNoItemsDisplay();
    }

    @Override
    public void onLoadFail(String message) {

        getViewState().reportLoadFailed(message);
        getViewState().showNoItemsDisplay();
    }
}
