package com.github.sadjz.models.login;

import com.google.gson.annotations.SerializedName;

public class TokenModel {

    @SerializedName("token")
    public String token;

    public TokenModel(String token){
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }


}
