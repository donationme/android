package com.github.sadjz.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.github.sadjz.R;


public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

    }

    public void onLoginPressed(View view) {
        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);


    }


    public void onRegisterPressed(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);

        startActivity(intent);

    }


}