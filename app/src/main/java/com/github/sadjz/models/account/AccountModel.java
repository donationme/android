package com.github.sadjz.models.account;

import com.github.sadjz.models.login.LoginModel;
import com.github.sadjz.models.user.UserModel;
import com.google.gson.annotations.SerializedName;

public class AccountModel {


    @SerializedName("auth")
    public LoginModel loginModel;
    @SerializedName("user")
    public UserModel userModel;


    public AccountModel(LoginModel loginModel , UserModel userModel){
        this.loginModel = loginModel;
        this.userModel = userModel;
    }

    public LoginModel getLoginModel() {
        return this.loginModel;
    }

    public UserModel getUserModel() {
        return this.userModel;
    }




}
