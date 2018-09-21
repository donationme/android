package com.github.sadjz.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.github.sadjz.R;

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


    public void onLoginPressed(View view) {
        this.validateForm();
        System.out.println(usernameTextfield.getText());
    }

    public boolean validateForm(){
        return true;
    }

}
