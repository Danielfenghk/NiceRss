package com.appsbrook.nicerss.di.modules;

import android.content.Context;

import com.appsbrook.nicerss.BuildConfig;
import com.appsbrook.nicerss.models.MyObjectBox;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;
import timber.log.Timber;

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
    BoxStore provideBoxStore(Context context) {

        BoxStore boxStore = MyObjectBox.builder().androidContext(context).build();
        if (BuildConfig.DEBUG) {
            boolean started = new AndroidObjectBrowser(boxStore).start(context);
            Timber.d("ObjectBrowser is started: " + started);
        }

        Timber.d("Using ObjectBox " + BoxStore.getVersion()
                + " (" + BoxStore.getVersionNative() + ")");

        return boxStore;
    }
}
