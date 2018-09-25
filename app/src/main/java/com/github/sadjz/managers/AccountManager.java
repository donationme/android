package com.github.sadjz.managers;

import android.content.Intent;
import android.util.Log;
import com.github.sadjz.controllers.Home;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.models.account.AccountModel;
import com.github.sadjz.models.login.LoginModel;
import com.github.sadjz.models.login.RestEndpoints;
import com.github.sadjz.models.login.TokenModel;
import com.github.sadjz.models.user.UserModel;

public class AccountManager {


    public void loginAccount(LoginModel loginModel, final RestCallback<UserModel> loginCallback) {
        final RestManager<LoginModel> loginRestManager = new RestManager<LoginModel>();


        try{

            RestCallback<TokenModel> tokenCallback = new RestCallback<TokenModel>() {
                @Override
                public void invoke(TokenModel tokenModel) {

                    try{

                        loginRestManager.getRequest(tokenModel.token, RestEndpoints.Account, loginCallback);

                    }catch (Exception e){
                        Log.d("User Fetch Error", e.getMessage());
                    }
                }
            };




            loginRestManager.postRequest(RestEndpoints.Token, loginModel, tokenCallback);


        }catch (Exception e){
            Log.d("Token Fetch Error", e.getMessage());
        }
    }


    public void createAccount(AccountModel accountModel, RestCallback<String> accountCallback) {

        final RestManager<AccountModel> loginRestManager = new RestManager<AccountModel>();

        try{


            loginRestManager.postRequest(RestEndpoints.Account, accountModel, accountCallback);

        }catch (Exception e){
            Log.d("Account Creation Error", e.getMessage());
        }

    }




}
