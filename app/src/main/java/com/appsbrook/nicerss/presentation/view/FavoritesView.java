package com.appsbrook.nicerss.presentation.view;

import com.appsbrook.nicerss.models.RssItem;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;


public interface FavoritesView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onLoadFavoritesFail();

    void showNoFavorites();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showFavorites(List<RssItem> rssItems);

    void onRemoveFavoritesSuccess();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateData(List<RssItem> rssItems);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onRemoveFavoritesFail();
}
