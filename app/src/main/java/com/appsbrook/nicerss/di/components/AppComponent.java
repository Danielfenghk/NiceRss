package com.appsbrook.nicerss.di.components;

import com.appsbrook.nicerss.di.modules.AppModule;
import com.appsbrook.nicerss.ui.activity.IntroductionActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(IntroductionActivity introductionActivity);

}
