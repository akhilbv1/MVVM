package com.architecture.mvvm.UI.DetailsModule;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.architecture.mvvm.R;
import com.architecture.mvvm.Storage.Response.SampleUserDetailsPojo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by akhil on 10/9/18.
 */

public class DetailsActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    private DetailsViewModel mDetailsViewModel;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private List<SampleUserDetailsPojo> mUsersList = new ArrayList<>();

    private ProgressDialog mProgressDialog;

    private DetailsActivityAdapterDataBinding mAdapter;

/*

    @BindingAdapter({"Success","ServerError"})
    public static void decideEvent(View view, Boolean isSuccess,Boolean isServerError){
        if (isServerError != null && isSuccess != null) {
            if (isSuccess && !isServerError) {
                Toast.makeText(view.getContext(), "Success", Toast.LENGTH_SHORT).show();

            }
        }
    }
*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rec);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mAdapter = new DetailsActivityAdapterDataBinding(mUsersList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);
        mDetailsViewModel = ViewModelProviders.of(this, mViewModelFactory).get(DetailsViewModel.class);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading");
        mDetailsViewModel.getData();

        //   Log.i("serverBindings:-", " message:- " + mDetailsViewModel.getServerBindings().getMessage());
        // Log.i("serverBindings:- ", " Visibility:- " + mDetailsViewModel.getServerBindings().getShowProgressDialog());
        getDataFromViewModel();

    }

    private void getDataFromViewModel() {

        mDetailsViewModel.getUsersList().observe(this, new Observer<List<SampleUserDetailsPojo>>() {
            @Override
            public void onChanged(@Nullable List<SampleUserDetailsPojo> sampleUserDetailsPojos) {
                mUsersList = sampleUserDetailsPojos;
                mAdapter.refreshData(mUsersList);
            }
        });

        mDetailsViewModel.getProgressDialogVisibility().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {

            }
        });
    }

/*
    private void getDataFromViewModel() {
        mProgressDialog.show();
        mDetailsViewModel.getUserDetailsObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<List<SampleUserDetailsPojo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }
                    @Override
                    public void onSuccess(Response<List<SampleUserDetailsPojo>> listResponse) {
                        mProgressDialog.dismiss();
                        mUsersList = listResponse.body();
                        mAdapter.refreshData(mUsersList);
                    }
                    @Override
                    public void onError(Throwable e) {
                        mProgressDialog.dismiss();
                        Toast.makeText(DetailsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDetailsViewModel.getCompositeDisposables().dispose();
    }
}
