package com.github.sadjz.models.account;

import com.github.sadjz.models.login.LoginModel;
import com.github.sadjz.models.user.UserModel;
import com.google.gson.annotations.SerializedName;
import java.lang.IllegalArgumentException;

/**
 * The type AccountModel
 */
@SuppressWarnings("unused")
public class AccountModel {

    @SuppressWarnings("FieldCanBeLocal")
    @SerializedName("auth")
  
    public LoginModel loginModel;
    @SerializedName("user")
    public UserModel userModel;

    /**
     * constructor for AccountModel
     *
     * @param loginModel the login model
     * @param userModel the user model
     * @throws IllegalArgumentException if argument is illegal
     */
    public AccountModel(LoginModel loginModel , UserModel userModel) {
        if (loginModel == null && userModel == null) {
            throw new IllegalArgumentException("Both login model and user model arguments are invalid");
        } else if (loginModel == null) {
            throw new IllegalArgumentException("The login model argument is invalid");
        } else if (userModel == null) {
            throw new IllegalArgumentException("The user model argument is invalid");
        }
        this.loginModel = loginModel;
        this.userModel = userModel;
    }

    public LoginModel getLoginModel() {
        return loginModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }
}
