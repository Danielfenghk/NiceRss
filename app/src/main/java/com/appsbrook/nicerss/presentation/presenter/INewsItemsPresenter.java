package com.appsbrook.nicerss.presentation.presenter;

import java.util.List;

public interface INewsItemsPresenter {
    void onLoadSuccess(List<String> items);

    void onLoadFail(String message);
}
