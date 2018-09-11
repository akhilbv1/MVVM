package com.architecture.mvvm.DI;

import com.architecture.mvvm.UI.DetailsModule.DetailsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by akhil on 10/9/18.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract DetailsActivity provideDetailsActivity();
}
