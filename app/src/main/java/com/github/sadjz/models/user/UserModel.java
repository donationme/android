package com.github.sadjz.models.user;

import com.google.gson.annotations.SerializedName;

public class UserModel {


    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("type")
    private UserType type;

    public UserModel(String name, String email, UserType type){
        this.name = name;
        this.email = email;
        this.type = type;
    }


    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }


    public UserType getType() {
        return this.type;
    }


}