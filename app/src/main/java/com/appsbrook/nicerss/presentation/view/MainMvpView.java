package com.appsbrook.nicerss.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndStrategy.class)
public interface MainMvpView extends MvpView {

    @StateStrategyType(AddToEndStrategy.class)
    void showCount(int count);

    void showSettings();
}
