package com.architecture.mvvm.Storage;

import android.arch.lifecycle.MutableLiveData;

import com.architecture.mvvm.Storage.Response.SampleUserDetailsPojo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by akhil on 10/9/18.
 */

public interface RestService {
    @GET("users")
    Single<Response<List<SampleUserDetailsPojo>>> getUserDetails();
}
