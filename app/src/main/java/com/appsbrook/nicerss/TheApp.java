package com.appsbrook.nicerss;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import timber.log.Timber;

public class TheApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initializeLogging();
        initializeDatabaseDebugging();
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