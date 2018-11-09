package com.github.sadjz.models.login;

import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("username")
    private final String username;

    @SerializedName("password")
    private final String password;

    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
