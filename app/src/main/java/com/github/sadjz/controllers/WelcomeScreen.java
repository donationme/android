package com.github.sadjz.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.github.sadjz.R;


public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

    }

    public void onLoginPressed(View view) {
        Intent intent = new Intent(this, Login.class);

        startActivity(intent);

    }

}