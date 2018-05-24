package com.appsbrook.nicerss.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface MainMvpView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openSettingsActivity();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void openAddNewSourceDialog();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openRssSourcesFragment();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openRssItemsFragment();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openFavoritesFragment();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openAboutActivity();
}
