package com.github.sadjz.models.account;

import com.github.sadjz.models.login.LoginModel;
import com.github.sadjz.models.user.UserModel;
import com.google.gson.annotations.SerializedName;

/**
 * The type AccountModel
 */
@SuppressWarnings("unused")
public class AccountModel {

    @SuppressWarnings("FieldCanBeLocal")
    @SerializedName("auth")
    private final LoginModel loginModel;

    @SuppressWarnings("FieldCanBeLocal")
    @SerializedName("user")
    private final UserModel userModel;

    /**
     * Instantiates a new Account model.
     *
     * @param loginModel the login model
     * @param userModel  the user model
     */
    public AccountModel(LoginModel loginModel, UserModel userModel) {
        this.loginModel = loginModel;
        this.userModel = userModel;
    }

}
