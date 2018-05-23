package com.appsbrook.nicerss.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface OneRssSourceView extends MvpView {

    void setAdapterData(List<String> categories);

    void promptFillEmptyName();

    void promptFillEmptyUrl();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onSaveRssSourceSuccess();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onSaveRssSourceFailure();

    void promptEnterCorrectUrl();
}
