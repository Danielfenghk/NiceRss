package com.appsbrook.nicerss.presentation.presenter;

import com.appsbrook.nicerss.models.RssItem;

import java.util.List;

public interface IRssItemsPresenter {

    void onLoadSuccess(List<RssItem> items);

    void onLoadFail(String message);
}
