package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.github.sadjz.R;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.AccountManager;
import com.github.sadjz.models.account.AccountModel;
import com.github.sadjz.models.login.LoginModel;
import com.github.sadjz.models.user.UserModel;
import com.github.sadjz.models.user.UserType;
import com.google.gson.internal.LinkedTreeMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameTextField;
    private EditText passwordTextField;
    private EditText nameTextField;
    private Spinner typeSpinner;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameTextField = findViewById(R.id.usernameTextField);
        passwordTextField = findViewById(R.id.passwordTextField);
        nameTextField = findViewById(R.id.nameTextField);
        typeSpinner = findViewById(R.id.typeSpinner);
        registerButton = findViewById(R.id.loginBtn);
        usernameTextField.requestFocus();

        ArrayAdapter<String> adapter =
                new ArrayAdapter(
                        this,
                        android.R.layout.simple_spinner_item,
                        UserType.values());
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        // Sets to user type
        typeSpinner.setSelection(1);
    }

    private void onLoginPressed(final View view) {

        final AccountManager accountManager = new AccountManager();

        final RegisterActivity currentActivity = this;
        final LoginModel loginModel =
                new LoginModel(
                        usernameTextField.getText().toString(),
                        passwordTextField.getText().toString());
        AccountModel accountModel =
                new AccountModel(
                        loginModel,
                        new UserModel(
                                nameTextField.getText().toString(),
                                usernameTextField.getText().toString(),
                                (UserType) typeSpinner.getSelectedItem()));

        registerButton.setText("Abort");

        registerButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        accountManager.abortRegistration();
                    }
                });

        RestCallback<LinkedTreeMap> accountCreationCallback =
                new RestCallback<LinkedTreeMap>() {
                    @Override
                    public void invokeSuccess(
                            LinkedTreeMap accountCreationResponse) {

                        if (accountCreationResponse.size() >= 1) {
                            Snackbar.make(
                                            view,
                                            accountCreationResponse
                                                    .values()
                                                    .toArray()[0]
                                                    .toString(),
                                            Snackbar.LENGTH_LONG)
                                    .setAction("Action", null)
                                    .show();
                            registerButton.setText("Register");
                            registerButton.setOnClickListener(
                                    new View.OnClickListener() {

                                        public void onClick(View v) {

                                            currentActivity.onLoginPressed(v);
                                        }
                                    });
                        }

                        RestCallback<UserModel> loginCallback =
                                new RestCallback<UserModel>() {
                                    @Override
                                    public void invokeSuccess(UserModel model) {

                                        HomeActivity.userModel = model;
                                        Intent intent =
                                                new Intent(
                                                        currentActivity,
                                                        HomeActivity.class);

                                        startActivity(intent);
                                    }

                                    @Override
                                    public void invokeFailure() {

                                        Snackbar.make(
                                                        view,
                                                        "Invalid Credentials!",
                                                        Snackbar.LENGTH_LONG)
                                                .setAction("Action", null)
                                                .show();
                                    }
                                };

                        if (accountCreationResponse.isEmpty()) {
                            accountManager.loginAccount(
                                    loginModel, loginCallback);
                        }
                    }

                    @Override
                    public void invokeFailure() {
                        registerButton.setText("Register");
                        registerButton.setOnClickListener(
                                new View.OnClickListener() {

                                    public void onClick(View v) {

                                        currentActivity.onLoginPressed(v);
                                    }
                                });
                        Snackbar.make(
                                        view,
                                        "Could not create the account at this time",
                                        Snackbar.LENGTH_LONG)
                                .setAction("Action", null)
                                .show();
                    }
                };

        accountManager.createAccount(accountModel, accountCreationCallback);
    }
}
