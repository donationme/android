package com.github.sadjz.models.login;

import com.google.gson.annotations.SerializedName;
import java.lang.IllegalArgumentException;

/**
 * The type Login model.
 */
@SuppressWarnings("unused")
public class LoginModel {

    @SuppressWarnings("FieldCanBeLocal")
    @SerializedName("username")
    private final String username;
    private final String password;
  
    /**
     * Instantiates a new Login model.
     *
     * @param username the username
     * @param password the password
     * @throws IllegalArgumentException if username or password is null
     */
    public LoginModel(String username , String password){
        if (username == null) {
            throw new IllegalArgumentException("The username cannot be null.");
        }
        if (password == null) {
            throw new IllegalArgumentException("The password cannot be null.");
        }
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
