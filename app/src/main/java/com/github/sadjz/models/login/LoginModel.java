package com.github.sadjz.models.login;

import com.google.gson.annotations.SerializedName;

/**
 * The type Login model.
 */
@SuppressWarnings("unused")
public class LoginModel {

    @SerializedName("username")
    private final String username;

    @SerializedName("password")
    private final String password;

    /**
     * Instantiates a new Login model.
     *
     * @param username the username
     * @param password the password
     */
    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
