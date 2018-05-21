package com.appsbrook.nicerss.interactors;

import com.appsbrook.nicerss.TheApp;
import com.appsbrook.nicerss.data.RssItem;
import com.appsbrook.nicerss.data.RssLoader;
import com.appsbrook.nicerss.presentation.presenter.IRssItemsPresenter;
import com.appsbrook.nicerss.presentation.presenter.RssItemsPresenter;

import java.util.List;

import javax.inject.Inject;

import hugo.weaving.DebugLog;

@DebugLog
public class RssItemsLoaderInteractor implements IRssItemsLoaderInteractor {

    private IRssItemsPresenter presenter;

    @Inject
    RssLoader rssLoader;

    public RssItemsLoaderInteractor(RssItemsPresenter presenter) {
        this.presenter = presenter;

        TheApp.getAppComponent().inject(this);
    }

    public void loadNewsItems() {
        rssLoader.loadRssItems( this);
    }

    @Override
    public void onLoadSuccess(List<RssItem> items) {

        presenter.onLoadSuccess(items);
    }

    @Override
    public void onLoadFail() {

        presenter.onLoadFail("Failed to load or parse RSS feed.");
    }
}
