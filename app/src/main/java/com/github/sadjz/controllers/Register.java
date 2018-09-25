package com.github.sadjz.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.R;
import com.github.sadjz.managers.AccountManager;
import com.github.sadjz.managers.RestManager;
import com.github.sadjz.models.account.AccountCreationResponse;
import com.github.sadjz.models.account.AccountModel;
import com.github.sadjz.models.login.LoginModel;
import com.github.sadjz.models.login.RestEndpoints;
import com.github.sadjz.models.login.TokenModel;
import com.github.sadjz.models.user.UserModel;
import com.github.sadjz.models.user.UserType;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {

    private EditText usernameTextfield;
    private EditText passwordTextfield;
    private EditText nameTextfield;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameTextfield = findViewById(R.id.usernameTextfield);
        passwordTextfield = findViewById(R.id.passwordTextfield);
        nameTextfield = findViewById(R.id.nameTextfield);
        usernameTextfield.requestFocus();

    }


    public void onLoginPressed(View view) {



        final RestManager<AccountModel> loginRestManager = new RestManager<AccountModel>();
        final AccountManager accountManager = new AccountManager();

        final Register currentActivity = this;
        final LoginModel loginModel = new LoginModel(usernameTextfield.getText().toString(),passwordTextfield.getText().toString());
        AccountModel accountModel = new AccountModel(loginModel,
                new UserModel(nameTextfield.getText().toString(), usernameTextfield.getText().toString(), UserType.User));


        RestCallback<List<AccountCreationResponse>> accountCreationCallback = new RestCallback<List<AccountCreationResponse>>() {
            @Override
            public void invoke(List<AccountCreationResponse> accountCreationResponse) {

                RestCallback<UserModel> loginCallback = new RestCallback<UserModel>() {
                    @Override
                    public void invoke(UserModel model) {

                        Home.userModel = model;
                        Intent intent = new Intent(currentActivity, Home.class);

                        startActivity(intent);

                    }
                };

                if (accountCreationResponse.size() == 0){
                    accountManager.loginAccount(loginModel,loginCallback);
                }

            }
        };


        accountManager.createAccount(accountModel,accountCreationCallback);

    }



}
