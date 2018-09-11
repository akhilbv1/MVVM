package com.architecture.mvvm.UI.DetailsModule;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.architecture.mvvm.R;
import com.architecture.mvvm.Storage.Response.SampleUserDetailsPojo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

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
        getDataFromViewModel();
    }

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

}
