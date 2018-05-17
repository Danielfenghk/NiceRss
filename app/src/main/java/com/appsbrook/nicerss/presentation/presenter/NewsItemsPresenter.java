package com.appsbrook.nicerss.presentation.presenter;


import com.appsbrook.nicerss.presentation.view.NewsItemsView;
import com.appsbrook.nicerss.ui.adapters.NewsItemsAdapter;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Arrays;
import java.util.List;

import hugo.weaving.DebugLog;
import timber.log.Timber;

@DebugLog
@InjectViewState
public class NewsItemsPresenter extends MvpPresenter<NewsItemsView> {

    public void loadNewsItems() {

        List<String> items = Arrays.asList("Batman", "Superman", "Spiderman", "Joker", "James");

        getViewState().updateNewsItems(items);
        getViewState().hideNoItemsDisplay();
    }
}
