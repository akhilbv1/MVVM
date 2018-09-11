package com.architecture.mvvm;

import com.architecture.mvvm.DI.AppComponent;
import com.architecture.mvvm.DI.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by akhil on 10/9/18.
 */

public class MVVM extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent daggerAppComponent = DaggerAppComponent.builder().application(this).build();
        daggerAppComponent.inject(this);
        return daggerAppComponent;
    }
}
