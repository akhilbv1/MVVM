package com.architecture.mvvm.DI;

import android.app.Application;

import com.architecture.mvvm.MVVM;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by akhil on 10/9/18.
 */

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,AppModule.class,ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(MVVM app);

    @Override
    void inject(DaggerApplication instance);


    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}
