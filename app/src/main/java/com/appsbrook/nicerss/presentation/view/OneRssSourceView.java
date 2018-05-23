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

    void promptEnterCorrectUrl();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onSaveRssSourceSuccess();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onSaveRssSourceFailure();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onEditRssSourceSuccess();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onEditRssSourceFailure();

    void setRssSourceName(String name);

    void setRssSourceUrl(String url);

    void setRssSourceCategory(int position);
}
