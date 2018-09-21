package com.architecture.mvvm.Storage.Request;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.util.Patterns;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by akhil on 11/9/18.
 */

public class LoginRequest extends BaseObservable {

    @Bindable
    public String message;

    @Bindable
    public Boolean success;

    @Bindable
    public Boolean serverError;

    private String email, password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
        notifyPropertyChanged(BR.success);
    }

    public Boolean getServerError() {
        return serverError;
    }

    public void setServerError(Boolean serverError) {
        this.serverError = serverError;
        notifyPropertyChanged(BR.serverError);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(getEmail()) && Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }
}
