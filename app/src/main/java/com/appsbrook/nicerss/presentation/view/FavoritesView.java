package com.appsbrook.nicerss.presentation.view;

import com.appsbrook.nicerss.models.RssItem;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface FavoritesView extends MvpView {

    void updateData(List<RssItem> rssItems);

    void showNoFavorites();

    void hideNoFavorites();

    void loadFavorites(List<RssItem> rssItems);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onRemoveFavoritesSuccess();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onRemoveFavoritesFail();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onLoadFavoritesFail();
}
