package com.appsbrook.nicerss;

import android.app.Application;

import com.appsbrook.nicerss.data.SettingsManager;
import com.appsbrook.nicerss.di.components.AppComponent;
import com.appsbrook.nicerss.di.components.DaggerAppComponent;
import com.appsbrook.nicerss.di.modules.AppModule;
import com.appsbrook.nicerss.models.MyObjectBox;
import com.appsbrook.nicerss.models.RssSource;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import hugo.weaving.DebugLog;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;
import timber.log.Timber;

@DebugLog
public class TheApp extends Application {

    private static AppComponent appComponent;

    @Inject
    SettingsManager settingsManager;

    private BoxStore boxStore;

    public BoxStore getBoxStore() {
        return boxStore;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initializeAppComponent();

        initializeLogging();
        initializeDatabaseDebugging();

        initializeObjectBox();
        initializeSourcesData();
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

    private void initializeObjectBox() {

        boxStore = MyObjectBox.builder().androidContext(TheApp.this).build();
        if (BuildConfig.DEBUG) {
            boolean started = new AndroidObjectBrowser(boxStore).start(this);
            Timber.d("ObjectBrowser is started: " + started);
        }

        Timber.d("Using ObjectBox " + BoxStore.getVersion()
                + " (" + BoxStore.getVersionNative() + ")");
    }

    private void initializeSourcesData() {

        appComponent.inject(this);

        if (settingsManager.isFirstLaunch()) {

            Box<RssSource> rssSourceBox = boxStore.boxFor(RssSource.class);

            RssSource rssSource = new RssSource();
            rssSource.setName("ArsTechnica");
            rssSource.setUrl("http://feeds.arstechnica.com/arstechnica/index?format=xml");
            rssSource.setCategory("Tech");

            long id = rssSourceBox.put(rssSource);
            RssSource storedRssSource = rssSourceBox.get(id);

            Timber.d("Stored Rss Source: " + storedRssSource);
        }
    }
}