package com.architecture.mvvm.Storage;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.opengl.Visibility;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by akhil on 11/9/18.
 */

public class ServerBindings extends BaseObservable{

    @Bindable
    public Boolean success;

    @Bindable
    public String message;

    @Bindable
    public Boolean serverError;

    @Bindable
    public int showProgressDialog;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
        notifyPropertyChanged(BR.success);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }

    public Boolean getServerError() {
        return serverError;
    }

    public void setServerError(Boolean serverError) {
        this.serverError = serverError;
        notifyPropertyChanged(BR.serverError);
    }

    public int getShowProgressDialog() {
        return showProgressDialog;
    }

    public void setShowProgressDialog(int showProgressDialog) {
        this.showProgressDialog = showProgressDialog;
        notifyPropertyChanged(BR.showProgressDialog);
    }
}
