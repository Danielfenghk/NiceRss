package com.appsbrook.nicerss.presentation.presenter;


import com.appsbrook.nicerss.data.RssItem;
import com.appsbrook.nicerss.interactors.NewsItemsLoaderInteractor;
import com.appsbrook.nicerss.presentation.view.NewsItemsView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import hugo.weaving.DebugLog;

@DebugLog
@InjectViewState
public class NewsItemsPresenter extends MvpPresenter<NewsItemsView>
        implements INewsItemsPresenter {

    private NewsItemsLoaderInteractor interactor;

    public NewsItemsPresenter() {

        interactor = new NewsItemsLoaderInteractor(this);
    }

    public void loadNewsItems(String url) {

        interactor.loadNewsItems(url);
    }

    @Override
    public void onLoadSuccess(List<RssItem> items) {

        getViewState().updateNewsItems(items);
        getViewState().hideNoItemsDisplay();
    }

    @Override
    public void onLoadFail(String message) {

        getViewState().reportLoadFailed(message);
        getViewState().showNoItemsDisplay();
    }
}
