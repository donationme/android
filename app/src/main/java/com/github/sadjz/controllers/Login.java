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
import com.github.sadjz.models.login.LoginModel;
import com.github.sadjz.models.login.RestEndpoints;
import com.github.sadjz.models.login.TokenModel;
import com.github.sadjz.models.user.UserModel;



public class Login extends AppCompatActivity {

    private EditText usernameTextfield;
    private EditText passwordTextfield;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameTextfield = findViewById(R.id.usernameTextfield);
        passwordTextfield = findViewById(R.id.passwordTextfield);
        usernameTextfield.requestFocus();

    }

    public void onBackToWelcomePress(View view) {

        Intent intent = new Intent(this, WelcomeScreen.class);

        startActivity(intent);
    }


    public void onLoginPressed(View view) {

        final Login currentActivity = this;
        LoginModel loginModel = new LoginModel(usernameTextfield.getText().toString(), passwordTextfield.getText().toString());
        RestCallback<UserModel> loginCallback = new RestCallback<UserModel>() {
            @Override
            public void invoke(UserModel model) {

                Home.userModel = model;
                Intent intent = new Intent(currentActivity, Home.class);

                startActivity(intent);

            }
        };


        AccountManager accountManager = new AccountManager();
        accountManager.loginAccount(loginModel,loginCallback);

    }




}
