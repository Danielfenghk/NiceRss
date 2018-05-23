package com.appsbrook.nicerss.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

public interface OneRssSourceView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setAdapterData(List<String> categories);
}
