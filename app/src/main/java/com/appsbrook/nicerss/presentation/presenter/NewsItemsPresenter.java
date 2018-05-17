package com.appsbrook.nicerss.presentation.presenter;


import com.appsbrook.nicerss.interactors.NewsItemsLoaderInteractor;
import com.appsbrook.nicerss.presentation.view.NewsItemsView;
import com.appsbrook.nicerss.ui.adapters.NewsItemsAdapter;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Arrays;
import java.util.List;

import hugo.weaving.DebugLog;
import timber.log.Timber;

@DebugLog
@InjectViewState
public class NewsItemsPresenter extends MvpPresenter<NewsItemsView>
        implements INewsItemsPresenter {

    private NewsItemsLoaderInteractor interactor;

    public NewsItemsPresenter() {

        interactor = new NewsItemsLoaderInteractor();
    }

    public void loadNewsItems() {

        interactor.loadNewsItems(this);
    }

    @Override
    public void onLoadSuccess(List<String> items) {

        getViewState().updateNewsItems(items);
        getViewState().hideNoItemsDisplay();
    }

    @Override
    public void onLoadFail(String message) {

        getViewState().reportLoadFailed(message);
        getViewState().showNoItemsDisplay();
    }
}
