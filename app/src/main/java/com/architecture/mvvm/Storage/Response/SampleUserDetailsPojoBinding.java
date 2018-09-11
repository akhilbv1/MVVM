package com.architecture.mvvm.Storage.Response;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.google.gson.annotations.SerializedName;

/**
 * Created by akhil on 11/9/18.
 */

public class SampleUserDetailsPojoBinding extends BaseObservable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    @Bindable
    private String name;

    @SerializedName("username")
    @Bindable
    private String username;

    @SerializedName("email")
    @Bindable
    private String email;

    @SerializedName("address")
    private Address address;

    @SerializedName("phone")
    private String phone;

    @SerializedName("website")
    @Bindable
    private String website;

    @SerializedName("company")
    private Company company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
        notifyPropertyChanged(BR.website);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
