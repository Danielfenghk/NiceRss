package com.appsbrook.nicerss.presentation.presenter;


import com.appsbrook.nicerss.interactors.FavoritesInteractor;
import com.appsbrook.nicerss.models.RssItem;
import com.appsbrook.nicerss.presentation.view.FavoritesView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

@InjectViewState
public class FavoritesPresenter extends MvpPresenter<FavoritesView>
        implements IFavoritesPresenter {

    private FavoritesInteractor interactor;

    public FavoritesPresenter() {
        this.interactor = new FavoritesInteractor(this);
    }

    public void loadFavorites() {

        interactor.loadFavorites();
    }

    @Override
    public void onLoadFavoritesFail() {
        getViewState().onLoadFavoritesFail();
    }

    @Override
    public void onLoadFavoritesSuccess(List<RssItem> rssItems) {

        if (rssItems.size() > 0) {
            getViewState().showFavorites(rssItems);
        } else {
            getViewState().showNoFavorites();
        }
    }

    @Override
    public void onRemoveFavoritesSuccess() {
        getViewState().updateData(null);
        getViewState().onRemoveFavoritesSuccess();
    }

    @Override
    public void onRemoveFavoritesFail() {
        getViewState().onRemoveFavoritesFail();
    }

    public void removeAllFavorites() {
        interactor.removeAllFavorites();
    }
}
