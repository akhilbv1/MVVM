package com.architecture.mvvm.DI;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.architecture.mvvm.DI.Annotation.ViewModelKey;
import com.architecture.mvvm.UI.DetailsModule.DetailsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by akhil on 10/9/18.
 */

@Module
public abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel.class)
    abstract ViewModel bindTestViewModel(DetailsViewModel testViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
}
