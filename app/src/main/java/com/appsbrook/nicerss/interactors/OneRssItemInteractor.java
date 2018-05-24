package com.appsbrook.nicerss.interactors;

import com.appsbrook.nicerss.TheApp;
import com.appsbrook.nicerss.data.DataManager;
import com.appsbrook.nicerss.models.RssItem;
import com.appsbrook.nicerss.presentation.presenter.IOneRssItemPresenter;

import javax.inject.Inject;

import timber.log.Timber;

public class OneRssItemInteractor implements IOneRssItemInteractor {

    private IOneRssItemPresenter presenter;

    @Inject
    DataManager dataManager;

    public OneRssItemInteractor(IOneRssItemPresenter presenter) {
        this.presenter = presenter;

        TheApp.getAppComponent().inject(this);
    }

    public boolean isRssItemSavedToFavorites(String link) {
        return dataManager.isRssItemSavedToFavorites(link);
    }

    public void addToFavorites(RssItem item) {

        long id = dataManager.saveToFavorites(item);

        if (id > 0) {
            presenter.onAddToFavoritesSuccess();
        } else {
            presenter.onAddToFavoritesFail();
        }

    }

    public void removeFromFavorites(RssItem theItem) {

        String link = theItem.getLink();
        RssItem item = dataManager.getRssItemByLink(link);

        if (item == null) {
            presenter.onRemoveFromFavoritesFail();
        }

        try {
            dataManager.removeFromFavorites(item);
            presenter.onRemoveFromFavoritesSuccess();
        } catch (Exception e) {
            presenter.onRemoveFromFavoritesFail();
        }
    }
}
