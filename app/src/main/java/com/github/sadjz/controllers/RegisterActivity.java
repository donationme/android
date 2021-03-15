package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
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

import java.util.Collection;

/**
 * The type Register activity.
 */

@SuppressWarnings("CyclicClassDependency")
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
        registerButton = findViewById(R.id.registerBtn);
        usernameTextField.requestFocus();

        ArrayAdapter<UserType> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        UserType.values());
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        // Sets to user type
        typeSpinner.setSelection(1);
    }

    /**
     * Called when register button pressed
     * @param view View of login view
     */
    @SuppressWarnings({"FeatureEnvy", "WeakerAccess"})
    public void onRegisterPressed(final View view) {

        final AccountManager accountManager = new AccountManager();
        Editable nameEditable = nameTextField.getText();
        Editable usernameEditable = usernameTextField.getText();
        Editable passwordEditable = passwordTextField.getText();

        final RegisterActivity currentActivity = this;
        final LoginModel loginModel =
                new LoginModel(
                        usernameEditable.toString(),
                        passwordEditable.toString());
        AccountModel accountModel =
                new AccountModel(
                        loginModel,
                        new UserModel(
                                nameEditable.toString(),
                                usernameEditable.toString(),
                                (UserType) typeSpinner.getSelectedItem()));

        registerButton.setText(R.string.abort);

        registerButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        accountManager.abortRegistration();
                    }
                });

        RestCallback<LinkedTreeMap> accountCreationCallback =
                new RestCallback<LinkedTreeMap>() {
                    @SuppressWarnings("unused")
                    @Override
                    public void invokeSuccess(
                            LinkedTreeMap accountCreationResponse) {

                        if (accountCreationResponse.size() >= 1) {
                            Collection accountErrorCollection = accountCreationResponse.values();
                            String accountErrorString = accountErrorCollection.
                                    toArray()[0].toString();
                            Snackbar snackbar = Snackbar.make(
                                            view,
                                            accountErrorString,
                                            Snackbar.LENGTH_LONG);
                            snackbar.setAction("Action", null);
                            snackbar.show();
                            registerButton.setText(R.string.register);
                            registerButton.setOnClickListener(
                                    new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v) {

                                            currentActivity.onRegisterPressed(v);
                                        }
                                    });
                        }

                        RestCallback<UserModel> loginCallback =
                                new RestCallback<UserModel>() {
                                    @SuppressWarnings("unused")
                                    @Override
                                    public void invokeSuccess(UserModel model) {

                                        HomeActivity.setUserModel(model);
                                        Intent intent =
                                                new Intent(
                                                        currentActivity,
                                                        HomeActivity.class);

                                        startActivity(intent);
                                    }

                                    @SuppressWarnings("unused")
                                    @Override
                                    public void invokeFailure() {

                                        Snackbar snackbar = Snackbar.make(
                                                        view,
                                                        "Invalid Credentials!",
                                                        Snackbar.LENGTH_LONG);
                                        snackbar.setAction("Action", null);
                                        snackbar.show();
                                    }
                                };

                        if (accountCreationResponse.isEmpty()) {
                            accountManager.loginAccount(
                                    loginModel, loginCallback);
                        }
                    }

                    @SuppressWarnings("unused")
                    @Override
                    public void invokeFailure() {
                        registerButton.setText(R.string.register);
                        registerButton.setOnClickListener(
                                new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {

                                        currentActivity.onRegisterPressed(v);
                                    }
                                });
                        Snackbar snackbar = Snackbar.make(
                                        view,
                                        "Could not create the account at this time",
                                        Snackbar.LENGTH_LONG);
                        snackbar.setAction("Action", null);
                        snackbar.show();
                    }
                };

        accountManager.createAccount(accountModel, accountCreationCallback);
    }
}
