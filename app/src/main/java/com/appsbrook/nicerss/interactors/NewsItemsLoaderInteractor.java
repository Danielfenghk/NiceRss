package com.appsbrook.nicerss.interactors;

import com.appsbrook.nicerss.presentation.presenter.INewsItemsPresenter;

import java.util.Arrays;
import java.util.List;

public class NewsItemsLoaderInteractor {


    public void loadNewsItems(INewsItemsPresenter presenter) {

        List<String> items = Arrays.asList("Batman", "Superman", "Spiderman", "Joker", "James");

        if (true) {
            presenter.onLoadSuccess(items);
        } else {
            String message = "Failed to load news items!";
            presenter.onLoadFail(message);
        }
    }
}
