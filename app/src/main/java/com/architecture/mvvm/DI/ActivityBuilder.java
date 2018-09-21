package com.architecture.mvvm.DI;

import com.architecture.mvvm.UI.DetailsModule.DetailsActivity;
import com.architecture.mvvm.UI.LoginModule.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by akhil on 10/9/18.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract DetailsActivity provideDetailsActivity();

    @ContributesAndroidInjector
    abstract LoginActivity provideLoginActivity();
}
