package com.appsbrook.nicerss.presentation.presenter;

import com.appsbrook.nicerss.models.RssItem;

import java.util.List;

public interface IFavoritesPresenter {
    void onLoadFavoritesFail();

    void onLoadFavoritesSuccess(List<RssItem> rssItems);
}
