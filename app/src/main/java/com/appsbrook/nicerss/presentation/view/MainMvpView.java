package com.appsbrook.nicerss.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface MainMvpView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showSettings();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showAddNewSourceDialog();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openRssSourcesActivity();
}
