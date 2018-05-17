package com.appsbrook.nicerss.di.modules;

import android.content.Context;

import com.appsbrook.nicerss.data.SettingsManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    SettingsManager provideSettingsManager(Context context) {

        return new SettingsManager(context);
    }
}
