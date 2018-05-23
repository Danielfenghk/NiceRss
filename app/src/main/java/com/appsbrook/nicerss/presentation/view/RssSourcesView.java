package com.appsbrook.nicerss.presentation.view;

import com.appsbrook.nicerss.models.RssSource;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

public interface RssSourcesView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setAdapterData(List<RssSource> allRssSources);
}
