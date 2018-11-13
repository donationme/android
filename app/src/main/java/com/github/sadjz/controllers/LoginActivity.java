package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.github.sadjz.R;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.AccountManager;
import com.github.sadjz.models.login.LoginModel;
import com.github.sadjz.models.user.UserModel;

/**
 * The type Login activity.
 */
@SuppressWarnings("CyclicClassDependency")
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

    public void onLoginPressed(final View view) {
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
        Editable usernameEditable = usernameTextField.getText();
        Editable passwordEditable = passwordTextField.getText();
        LoginModel loginModel =
                new LoginModel(
                        usernameEditable.toString(),
                        passwordEditable.toString());
        @SuppressWarnings("unused") RestCallback<UserModel> loginCallback =
                new RestCallback<UserModel>() {
                    @SuppressWarnings("unused")
                    @Override
                    public void invokeSuccess(UserModel model) {

                        HomeActivity.setUserModel(model);
                        Intent intent =
                                new Intent(currentActivity, HomeActivity.class);
                        finishAffinity();
                        startActivity(intent);
                    }

                    @SuppressWarnings("unused")
                    @Override
                    public void invokeFailure() {
                        Snackbar snackbar = Snackbar.make(
                                        view,
                                        "Please enter the correct credentials",
                                        Snackbar.LENGTH_LONG);

                        snackbar.setAction("Action", null);
                        snackbar.show();

                        loginBtn.setText("Login");
                        loginBtn.setOnClickListener(
                                new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {

                                        currentActivity.onLoginPressed(v);
                                    }
                                });
                    }
                };

        accountManager.loginAccount(loginModel, loginCallback);
    }
}
