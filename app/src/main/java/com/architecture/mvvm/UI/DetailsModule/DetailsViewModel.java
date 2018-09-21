package com.architecture.mvvm.UI.DetailsModule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.architecture.mvvm.Storage.DataManager;
import com.architecture.mvvm.Storage.Response.SampleUserDetailsPojo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/*
 * Created by akhil on 10/9/18.
 */

public class DetailsViewModel extends AndroidViewModel {

    public final MutableLiveData<Integer> progressDialogVisibility = new MutableLiveData<>();

    public final MutableLiveData<String> serverMessage = new MutableLiveData<>();

    private Single<Response<List<SampleUserDetailsPojo>>> userDetailsObservable;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MutableLiveData<List<SampleUserDetailsPojo>> usersListLiveData = new MutableLiveData<>();


    @Inject
    public DetailsViewModel(@NonNull DataManager dataManager, @NonNull Application application) {
        super(application);
        userDetailsObservable = dataManager.getUserDetailsObservable();
    }

    public MutableLiveData<Integer> getProgressDialogVisibility() {
        return progressDialogVisibility;
    }

    public MutableLiveData<String> getServerMessage() {
        return serverMessage;
    }

    public MutableLiveData<List<SampleUserDetailsPojo>> getUsersList() {
        return usersListLiveData;
    }

    public void getData() {
        progressDialogVisibility.setValue(View.VISIBLE);
        userDetailsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<List<SampleUserDetailsPojo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Response<List<SampleUserDetailsPojo>> listResponse) {
                        progressDialogVisibility.setValue(View.GONE);
                        if (listResponse.code() == 200) {
                            //   usersList = listResponse.body();
                            usersListLiveData.setValue(listResponse.body());
                        } else {
                            serverMessage.setValue("Server Error");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialogVisibility.setValue(View.GONE);
                        Log.i("message", "" + e.getMessage());
                        serverMessage.setValue("Server Error");
                    }
                });
    }

    public CompositeDisposable getCompositeDisposables() {
        return compositeDisposable;
    }


    public Single<Response<List<SampleUserDetailsPojo>>> getUserDetailsObservable() {
        return userDetailsObservable;
    }
}
