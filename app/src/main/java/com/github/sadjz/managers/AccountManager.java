package com.github.sadjz.managers;

import android.util.Log;
import com.github.sadjz.consts.RestEndpoints;
import com.github.sadjz.controllers.HomeActivity;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.models.account.AccountModel;
import com.github.sadjz.models.login.LoginModel;
import com.github.sadjz.models.login.TokenModel;
import com.github.sadjz.models.user.UserModel;
import com.google.gson.internal.LinkedTreeMap;

import okhttp3.Callback;

/**
 * The type Account manager.
 */

@SuppressWarnings({"CyclicClassDependency", "unused"})
public class AccountManager {

    private final RestManager loginRestManager = new RestManager();
    private final RestManager accountRestManager = new RestManager();

    /**
     * Login account.
     *
     * @param loginModel    the login model
     * @param loginCallback the login callback
     */
    @SuppressWarnings("FeatureEnvy")
    public void loginAccount(
            LoginModel loginModel,
            final RestCallback<UserModel> loginCallback) {

        try {

            //noinspection unused
            @SuppressWarnings("unused") Callback tokenCallback =
                    new RestCallback<TokenModel>() {
                        @SuppressWarnings("unused")
                        @Override
                        public void invokeSuccess(TokenModel tokenModel) {

                            try {
                                HomeActivity.setTokenModel(tokenModel);
                                loginRestManager.getRequest(
                                        tokenModel.getToken(),
                                        RestEndpoints.Account,
                                        loginCallback,
                                        "");

                            } catch (Exception e) {
                                Log.d("User Fetch Error", e.getMessage());
                                loginCallback.invokeFailure();
                            }
                        }

                        @SuppressWarnings("unused")
                        @Override
                        public void invokeFailure() {
                            loginCallback.invokeFailure();
                        }
                    };

            loginRestManager.postRequest(
                    RestEndpoints.Token, loginModel, tokenCallback, "");

        } catch (Exception e) {
            Log.d("Token Fetch Error", e.getMessage());
            loginCallback.invokeFailure();
        }
    }

    /**
     * Abort login.
     */
    public void abortLogin() {
        this.loginRestManager.abortRequest();
    }

    /**
     * Abort registration.
     */
    public void abortRegistration() {
        this.accountRestManager.abortRequest();
    }

    /**
     * Create account.
     *
     * @param accountModel    the account model
     * @param accountCallback the account callback
     */
    public void createAccount(
            AccountModel accountModel,
            RestCallback<LinkedTreeMap> accountCallback) {

        try {

            accountRestManager.postRequest(
                    RestEndpoints.Account, accountModel, accountCallback, "");

        } catch (Exception e) {
            accountCallback.invokeFailure();
        }
    }
}
