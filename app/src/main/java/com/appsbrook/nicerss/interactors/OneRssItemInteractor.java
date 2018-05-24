package com.appsbrook.nicerss.interactors;

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

        AddToFavoritesTask task = new AddToFavoritesTask(dataManager, presenter);
        task.execute(item);
    }

    public void removeFromFavorites(RssItem item) {

        RemoveFromFavoritesTask task = new RemoveFromFavoritesTask(dataManager, presenter);
        task.execute(item);
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

    private static class RemoveFromFavoritesTask extends AsyncTask<RssItem, Void, Boolean> {

        private DataManager dataManager;
        private IOneRssItemPresenter presenter;

        public RemoveFromFavoritesTask(DataManager dataManager, IOneRssItemPresenter presenter) {
            this.dataManager = dataManager;
            this.presenter = presenter;
        }

        @Override
        protected Boolean doInBackground(RssItem... rssItems) {

            RssItem item = rssItems[0];
            String link = item.getLink();
            RssItem itemToRemove = dataManager.getRssItemByLink(link);

            if (itemToRemove == null) {
                return false;
            }

            try {
                dataManager.removeFromFavorites(itemToRemove);
            } catch (Exception e) {
                Timber.e(e);
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean isRemoveSuccess) {

            if (isRemoveSuccess) {
                presenter.onRemoveFromFavoritesSuccess();
            } else {
                presenter.onRemoveFromFavoritesFail();
            }
        }
    }
}
