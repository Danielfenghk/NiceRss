package com.appsbrook.nicerss.interactors;

import com.appsbrook.nicerss.data.RssItem;
import com.appsbrook.nicerss.data.RssLoader;
import com.appsbrook.nicerss.presentation.presenter.INewsItemsPresenter;
import com.appsbrook.nicerss.presentation.presenter.NewsItemsPresenter;

import java.util.List;

import hugo.weaving.DebugLog;

@DebugLog
public class NewsItemsLoaderInteractor implements INewsItemsLoaderInteractor {

    private INewsItemsPresenter presenter;

    private RssLoader rssLoader;

    public NewsItemsLoaderInteractor(NewsItemsPresenter presenter) {
        this.presenter = presenter;

        rssLoader = new RssLoader();
    }

    public void loadNewsItems(String url) {

        rssLoader.loadRssItems(url, this);
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
