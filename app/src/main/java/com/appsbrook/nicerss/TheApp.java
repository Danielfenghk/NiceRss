package com.appsbrook.nicerss;

import android.app.Application;

import com.appsbrook.nicerss.data.DataManager;
import com.appsbrook.nicerss.data.SettingsManager;
import com.appsbrook.nicerss.di.components.AppComponent;
import com.appsbrook.nicerss.di.components.DaggerAppComponent;
import com.appsbrook.nicerss.di.modules.AppModule;
import com.appsbrook.nicerss.models.RssCategory;
import com.appsbrook.nicerss.models.RssSource;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import hugo.weaving.DebugLog;
import timber.log.Timber;

// TODO edit rss sources activity
// TODO use navigation drawer to navigate between RssItemsFragment and RssSourcesFragment

@DebugLog
public class TheApp extends Application {

    private static AppComponent appComponent;

    @Inject
    SettingsManager settingsManager;

    @Inject
    DataManager dataManager;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initializeAppComponent();

        initializeLogging();
        initializeDatabaseDebugging();

        initializeAppData();
    }

    private void initializeAppComponent() {
        Timber.d("init");

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    private void initializeLogging() {

        if (BuildConfig.DEBUG) {

            Logger.addLogAdapter(new AndroidLogAdapter());

            Timber.plant(new Timber.DebugTree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    Logger.log(priority, tag, message, t);
                }
            });
        }
    }

    private void initializeDatabaseDebugging() {

        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this);
    }


    private void initializeAppData() {

        appComponent.inject(this);

        if (settingsManager.isFirstLaunch()) {

            storeRssCategory("Tech", R.drawable.ic_tech);
            storeRssCategory("News", R.drawable.ic_news_rss_category);
            storeRssCategory("Business", R.drawable.ic_business_rss_category);

            storeRssSource("ArsTechnica",
                    "http://feeds.arstechnica.com/arstechnica/index?format=xml",
                    "Tech");

            storeRssSource("Reuters",
                    "http://feeds.reuters.com/reuters/topNews?format=xml",
                    "News");

            storeRssSource("IGN",
                    "http://feeds.ign.com/ign/all?format=xml",
                    "Business");
        }
    }

    private void storeRssCategory(String title, int image) {

        RssCategory rssCategory = new RssCategory();
        rssCategory.setTitle(title);
        rssCategory.setImage(image);

        long id = dataManager.putRssCategory(rssCategory);
        RssCategory storedRssCategory = dataManager.getRssCategory(id);

        Timber.d("Stored Rss Category: " + storedRssCategory);
    }

    private void storeRssSource(String name,
                                String url, String category) {

        RssSource rssSource = new RssSource();
        rssSource.setName(name);
        rssSource.setUrl(url);
        rssSource.setCategory(category);

        long id = dataManager.putRssSource(rssSource);
        RssSource storedRssSource = dataManager.getRssSource(id);

        Timber.d("Stored Rss Source: " + storedRssSource);
    }
}