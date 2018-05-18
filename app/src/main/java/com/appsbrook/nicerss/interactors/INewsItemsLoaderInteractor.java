package com.appsbrook.nicerss.interactors;

import com.appsbrook.nicerss.data.RssItem;

import java.util.List;

public interface INewsItemsLoaderInteractor {

    void onLoadSuccess(List<RssItem> items);

    void onLoadFail();
}
