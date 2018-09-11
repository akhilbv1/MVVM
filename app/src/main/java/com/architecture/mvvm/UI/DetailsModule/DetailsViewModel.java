package com.architecture.mvvm.UI.DetailsModule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.architecture.mvvm.Storage.DataManager;
import com.architecture.mvvm.Storage.Response.SampleUserDetailsPojo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by akhil on 10/9/18.
 */

public class DetailsViewModel extends AndroidViewModel {

    Single<Response<List<SampleUserDetailsPojo>>> userDetailsObservable;

    @Inject
    public DetailsViewModel(@NonNull DataManager dataManager,@NonNull Application application) {
        super(application);
        userDetailsObservable = dataManager.getUserDetailsObservable();
    }

    public Single<Response<List<SampleUserDetailsPojo>>> getUserDetailsObservable(){
        return userDetailsObservable;
    }
}
