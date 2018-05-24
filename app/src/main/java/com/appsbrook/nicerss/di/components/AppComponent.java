package com.appsbrook.nicerss.di.components;

import com.appsbrook.nicerss.TheApp;
import com.appsbrook.nicerss.di.modules.AppModule;
import com.appsbrook.nicerss.interactors.FavoritesInteractor;
import com.appsbrook.nicerss.interactors.OneRssItemInteractor;
import com.appsbrook.nicerss.interactors.RssItemsLoaderInteractor;
import com.appsbrook.nicerss.presentation.presenter.OneRssSourcePresenter;
import com.appsbrook.nicerss.presentation.presenter.RssSourcesPresenter;
import com.appsbrook.nicerss.ui.activity.IntroductionActivity;
import com.appsbrook.nicerss.ui.adapters.RssItemsAdapter;
import com.appsbrook.nicerss.ui.fragment.RssSourcesFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(IntroductionActivity introductionActivity);

    void inject(RssItemsLoaderInteractor rssItemsLoaderInteractor);

    void inject(TheApp theApp);

    void inject(RssItemsAdapter rssItemsAdapter);

    void inject(RssSourcesFragment rssSourcesFragment);

    void inject(RssSourcesPresenter rssSourcesPresenter);

    void inject(OneRssSourcePresenter oneRssSourcePresenter);

    void inject(OneRssItemInteractor oneRssItemInteractor);

    void inject(FavoritesInteractor favoritesInteractor);
}
