package com.appsbrook.nicerss.interactors;

import android.os.AsyncTask;

import com.appsbrook.nicerss.TheApp;
import com.appsbrook.nicerss.data.DataManager;
import com.appsbrook.nicerss.models.RssItem;
import com.appsbrook.nicerss.presentation.presenter.IFavoritesPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class FavoritesInteractor implements IFavoritesInteractor {

    private IFavoritesPresenter presenter;

    @Inject
    DataManager dataManager;

    public FavoritesInteractor(IFavoritesPresenter presenter) {
        this.presenter = presenter;

        TheApp.getAppComponent().inject(this);
    }

    public void loadFavorites() {

        LoadFavoritesTask task = new LoadFavoritesTask(presenter, dataManager);
        task.execute();
    }

    private static class LoadFavoritesTask extends AsyncTask<Void, Void, List<RssItem>>{

        private final IFavoritesPresenter presenter;
        private final DataManager dataManager;

        public LoadFavoritesTask(IFavoritesPresenter presenter, DataManager dataManager) {
            this.presenter = presenter;
            this.dataManager = dataManager;
        }

        @Override
        protected List<RssItem> doInBackground(Void... voids) {

            List<RssItem> favorites = new ArrayList<>();

            try {
                favorites = dataManager.getFavorites();

            } catch (Exception e) {
                Timber.e(e, "Failed to load all favorites.");
                presenter.onLoadFavoritesFail();
            }
            return favorites;
        }

        @Override
        protected void onPostExecute(List<RssItem> rssItems) {

            presenter.onLoadFavoritesSuccess(rssItems);
        }
    }
}
