package com.appsbrook.nicerss.presentation.presenter;

import com.appsbrook.nicerss.TheApp;
import com.appsbrook.nicerss.data.DataManager;
import com.appsbrook.nicerss.models.RssSource;
import com.appsbrook.nicerss.presentation.view.RssSourcesView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class RssSourcesPresenter extends MvpPresenter<RssSourcesView> {

    @Inject
    DataManager dataManager;

    public RssSourcesPresenter() {
        TheApp.getAppComponent().inject(this);
    }

    public void setAdapterData() {

        List<RssSource> allRssSources = dataManager.getAllRssSources();
        getViewState().setAdapterData(allRssSources);
    }

    public void deleteRssSource(RssSource rssSource) {

        try {
            dataManager.deleteRssSource(rssSource);

            List<RssSource> allRssSources = dataManager.getAllRssSources();
            getViewState().confirmDeleteSuccess(allRssSources);

        } catch (Exception e) {
            getViewState().reportDeleteFailure();
        }
    }
}
