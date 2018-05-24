package com.appsbrook.nicerss.interactors;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.appsbrook.nicerss.TheApp;
import com.appsbrook.nicerss.data.DataManager;
import com.appsbrook.nicerss.models.RssItem;
import com.appsbrook.nicerss.presentation.presenter.IOneRssItemPresenter;

import javax.inject.Inject;

import hugo.weaving.DebugLog;
import timber.log.Timber;

@DebugLog
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

        AddToFavoritesTask addToFavoritesTask = new AddToFavoritesTask(dataManager, presenter);
        addToFavoritesTask.execute(item);
    }

    private static class AddToFavoritesTask extends AsyncTask<RssItem, Void, Long> {

        private DataManager dataManager;
        private IOneRssItemPresenter presenter;

        AddToFavoritesTask(DataManager dataManager, IOneRssItemPresenter presenter) {
            this.dataManager = dataManager;
            this.presenter = presenter;
        }

        @Override
        protected Long doInBackground(RssItem... rssItems) {

            RssItem item = rssItems[0];
            long id = dataManager.saveToFavorites(item);
            Timber.d("long id = dataManager.saveToFavorites(item);");
            return id;
        }

        @Override
        protected void onPostExecute(Long id) {

            if (id > 0) {
                Timber.d("presenter.onAddToFavoritesSuccess();");
                presenter.onAddToFavoritesSuccess();
            } else {
                presenter.onAddToFavoritesFail();
            }
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
