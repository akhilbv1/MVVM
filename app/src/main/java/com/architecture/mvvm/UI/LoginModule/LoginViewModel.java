package com.architecture.mvvm.UI.LoginModule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.architecture.mvvm.Storage.DataManager;
import com.architecture.mvvm.Storage.Request.LoginRequest;

import javax.inject.Inject;

/**
 * Created by akhil on 11/9/18.
 */

public class LoginViewModel extends AndroidViewModel {

    private LoginRequest loginRequest = new LoginRequest();

    @Inject
    public LoginViewModel(@NonNull DataManager dataManager, @NonNull Application application) {
        super(application);
    }

    public void onPasswordTextChanged(CharSequence s) {
        loginRequest.setPassword(s.toString());
    }

    public void onEmailTextChanged(CharSequence s) {
        loginRequest.setEmail(s.toString());
    }

    private void setToastMessage(String message) {
        loginRequest.setMessage(message);
    }


    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void onClickLogin() {
        if (loginRequest.isInputDataValid()) {
            if (loginRequest.getEmail().equalsIgnoreCase("Akhilbv1@gmail.com") && loginRequest.getPassword().equalsIgnoreCase("123")) {
                setToastMessage("LoginSuccess");
                loginRequest.setSuccess(true);
                loginRequest.setServerError(false);
            } else {
                setToastMessage("Invalid Credentials");
                loginRequest.setSuccess(false);
                loginRequest.setServerError(false);
            }
        } else {
            loginRequest.setServerError(true);
            loginRequest.setSuccess(false);
        }
    }


}
