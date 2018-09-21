package com.architecture.mvvm.UI.LoginModule;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.architecture.mvvm.R;
import com.architecture.mvvm.UI.DetailsModule.DetailsActivity;
import com.architecture.mvvm.databinding.ActivityLoginBinding;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by akhil on 11/9/18.
 */

public class LoginActivity extends DaggerAppCompatActivity {


    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    private LoginViewModel loginViewModel;

    @BindingAdapter({"ToastMessage"})
    public static void ShowMessage(View view, String message) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }


    @BindingAdapter({"Success","ServerError"})
    public static void decideEvent(View view, Boolean isSuccess,Boolean isServerError) {
        if (isServerError != null && isSuccess != null) {
            if (isSuccess && !isServerError) {
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                view.getContext().startActivity(intent);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        loginViewModel = ViewModelProviders.of(this, mViewModelFactory).get(LoginViewModel.class);
        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setLoginViewModel(loginViewModel);
        activityLoginBinding.setLoginRequest(loginViewModel.getLoginRequest());
        activityLoginBinding.executePendingBindings();

        Log.i("message",""+loginViewModel.getLoginRequest().message);
    }
}
