package com.appsbrook.nicerss.presentation.presenter;

public interface IOneRssItemPresenter {
    void onRemoveFromFavoritesFail();

    void onRemoveFromFavoritesSuccess();

    void onAddToFavoritesSuccess();

    void onAddToFavoritesFail();
}
