package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.github.sadjz.R;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.AccountManager;
import com.github.sadjz.models.login.LoginModel;
import com.github.sadjz.models.user.UserModel;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameTextField;
    private EditText passwordTextField;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameTextField = findViewById(R.id.usernameTextField);
        passwordTextField = findViewById(R.id.passwordTextField);
        usernameTextField.requestFocus();
        loginBtn = findViewById(R.id.loginBtn);
    }

    private void onLoginPressed(final View view) {
        final AccountManager accountManager = new AccountManager();
        loginBtn.setText("Abort");
        final LoginActivity currentActivity = this;

        loginBtn.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        accountManager.abortLogin();
                    }
                });

        LoginModel loginModel =
                new LoginModel(
                        usernameTextField.getText().toString(),
                        passwordTextField.getText().toString());
        RestCallback<UserModel> loginCallback =
                new RestCallback<UserModel>() {
                    @Override
                    public void invokeSuccess(UserModel model) {

                        HomeActivity.userModel = model;
                        Intent intent =
                                new Intent(currentActivity, HomeActivity.class);
                        finishAffinity();
                        startActivity(intent);
                    }

                    @Override
                    public void invokeFailure() {
                        Snackbar.make(
                                        view,
                                        "Please enter the correct credentials",
                                        Snackbar.LENGTH_LONG)
                                .setAction("Action", null)
                                .show();

                        loginBtn.setText("Login");
                        loginBtn.setOnClickListener(
                                new View.OnClickListener() {

                                    public void onClick(View v) {

                                        currentActivity.onLoginPressed(v);
                                    }
                                });
                    }
                };

        accountManager.loginAccount(loginModel, loginCallback);
    }
}
