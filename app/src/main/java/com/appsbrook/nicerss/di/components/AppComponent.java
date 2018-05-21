package com.appsbrook.nicerss.di.components;

import com.appsbrook.nicerss.TheApp;
import com.appsbrook.nicerss.di.modules.AppModule;
import com.appsbrook.nicerss.interactors.RssItemsLoaderInteractor;
import com.appsbrook.nicerss.ui.activity.IntroductionActivity;
import com.appsbrook.nicerss.ui.adapters.RssItemsAdapter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(IntroductionActivity introductionActivity);

    void inject(RssItemsLoaderInteractor rssItemsLoaderInteractor);

    void inject(TheApp theApp);

    void inject(RssItemsAdapter rssItemsAdapter);
}
