package com.github.sadjz.controllers;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.R;
import com.github.sadjz.managers.AccountManager;
import com.github.sadjz.managers.RestManager;
import com.github.sadjz.models.account.ServerResponse;
import com.github.sadjz.models.account.AccountModel;
import com.github.sadjz.models.login.LoginModel;

import com.github.sadjz.models.user.UserModel;
import com.github.sadjz.models.user.UserType;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameTextfield;
    private EditText passwordTextfield;
    private EditText nameTextfield;
    private Spinner typeSpinner;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameTextfield = findViewById(R.id.usernameTextfield);
        passwordTextfield = findViewById(R.id.passwordTextfield);
        nameTextfield = findViewById(R.id.nameTextfield);
        typeSpinner = findViewById(R.id.typeSpinner);
        registerButton = findViewById(R.id.registerBtn);
        usernameTextfield.requestFocus();

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, UserType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        //Sets to user type
        typeSpinner.setSelection(1);

    }


    public void onLoginPressed(final View view) {


        final AccountManager accountManager = new AccountManager();

        final RegisterActivity currentActivity = this;
        final LoginModel loginModel = new LoginModel(usernameTextfield.getText().toString(),passwordTextfield.getText().toString());
        AccountModel accountModel = new AccountModel(loginModel,
                new UserModel(nameTextfield.getText().toString(), usernameTextfield.getText().toString(), (UserType) typeSpinner.getSelectedItem()));

        registerButton.setText("Abort");



        registerButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                accountManager.abortRegistration();
            }
        });


        RestCallback<ServerResponse[]> accountCreationCallback = new RestCallback<ServerResponse[]>() {
            @Override
            public void invokeSuccess(ServerResponse[] accountCreationResponse) {

                if (accountCreationResponse.length >= 1){
                    Snackbar.make(view, accountCreationResponse[0].getErrorMessage(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

                RestCallback<UserModel> loginCallback = new RestCallback<UserModel>() {
                    @Override
                    public void invokeSuccess(UserModel model) {

                        HomeActivity.userModel = model;
                        Intent intent = new Intent(currentActivity, HomeActivity.class);

                        startActivity(intent);

                    }

                    @Override
                    public void invokeFailure(){

                        Snackbar.make(view, "Invalid Credentials!", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                    }

                };

                if (accountCreationResponse.length == 0){
                    accountManager.loginAccount(loginModel,loginCallback);
                }

            }

            @Override
            public void invokeFailure(){
                registerButton.setText("LoginActivity");
                registerButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        currentActivity.onLoginPressed(v);
                    }
                });
                Snackbar.make(view, "Could not create the account at this time", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        };


        accountManager.createAccount(accountModel,accountCreationCallback);

    }





}
