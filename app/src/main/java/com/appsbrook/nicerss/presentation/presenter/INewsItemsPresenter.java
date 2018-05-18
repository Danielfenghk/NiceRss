package com.appsbrook.nicerss.presentation.presenter;

import com.appsbrook.nicerss.data.RssItem;

import java.util.List;

public interface INewsItemsPresenter {

    void onLoadSuccess(List<RssItem> items);

    void onLoadFail(String message);
}
