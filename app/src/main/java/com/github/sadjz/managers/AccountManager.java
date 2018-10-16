package com.github.sadjz.managers;

import android.util.Log;

import com.github.sadjz.controllers.Home;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.models.account.ServerResponse;
import com.github.sadjz.models.account.AccountModel;
import com.github.sadjz.models.login.LoginModel;
import com.github.sadjz.models.login.RestEndpoints;
import com.github.sadjz.models.login.TokenModel;
import com.github.sadjz.models.user.UserModel;

import java.util.List;

public class AccountManager {


    private final RestManager loginRestManager = new RestManager();
    private final RestManager accountRestManager = new RestManager();


    public void loginAccount(LoginModel loginModel, final RestCallback<UserModel> loginCallback) {


        try{

            RestCallback<TokenModel> tokenCallback = new RestCallback<TokenModel>() {
                    @Override
                    public void invokeSuccess(TokenModel tokenModel) {

                        try{
                            Home.tokenModel = tokenModel;
                            loginRestManager.getRequest(tokenModel.token, RestEndpoints.Account, loginCallback);

                        }catch (Exception e){
                            Log.d("User Fetch Error", e.getMessage());
                            loginCallback.invokeFailure();
                        }
                    }
                @Override
                public void invokeFailure(){
                    loginCallback.invokeFailure();

                }



                };




            loginRestManager.postRequest(RestEndpoints.Token, loginModel, tokenCallback);


        }catch (Exception e){
            Log.d("Token Fetch Error", e.getMessage());
            loginCallback.invokeFailure();

        }
    }


    public void abortLogin(){
        this.loginRestManager.abortRequest();
    }

    public void abortRegistration(){
        this.accountRestManager.abortRequest();

    }




    public void createAccount(AccountModel accountModel, RestCallback<ServerResponse[]> accountCallback) {


        try{

            accountRestManager.postRequest(RestEndpoints.Account, accountModel, accountCallback);

        }catch (Exception e){
            accountCallback.invokeFailure();
        }

    }




}
