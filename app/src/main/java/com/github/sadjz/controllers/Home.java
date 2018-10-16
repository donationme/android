package com.github.sadjz.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.github.sadjz.R;
import com.github.sadjz.models.login.TokenModel;
import com.github.sadjz.models.user.UserModel;

public class Home extends AppCompatActivity {

    //TODO: Change this to a Singleton later on
    static UserModel userModel;
    public static TokenModel tokenModel;


    private TextView typeLabel;
    private TextView emailLabel;
    private TextView nameLabel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        typeLabel = findViewById(R.id.typeLabel);
        emailLabel = findViewById(R.id.emailLabel);
        nameLabel = findViewById(R.id.nameLabel);
        typeLabel.setText(Home.userModel.getType().name());
        emailLabel.setText(Home.userModel.getEmail());
        nameLabel.setText(Home.userModel.getName());

    }


    public void onLogoutPressed(View view) {
        Intent intent = new Intent(this, WelcomeScreen.class);
        startActivity(intent);
    }

}
