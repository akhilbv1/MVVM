package com.architecture.mvvm.Storage;

import android.arch.lifecycle.MutableLiveData;

import com.architecture.mvvm.Storage.Response.SampleUserDetailsPojo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by akhil on 10/9/18.
 */

@Singleton
public class DataManager {


    RestService restService;

    @Inject
    public DataManager(RestService restService) {
        this.restService = restService;
    }

    public Single<Response<List<SampleUserDetailsPojo>>> getUserDetailsObservable(){
        return restService.getUserDetails();
    }

}
