package com.appsbrook.nicerss;

import android.app.Application;

import com.appsbrook.nicerss.di.components.AppComponent;
import com.appsbrook.nicerss.di.components.DaggerAppComponent;
import com.appsbrook.nicerss.di.modules.AppModule;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import hugo.weaving.DebugLog;
import timber.log.Timber;

@DebugLog
public class TheApp extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initializeAppComponent();

        initializeLogging();
        initializeDatabaseDebugging();
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
}