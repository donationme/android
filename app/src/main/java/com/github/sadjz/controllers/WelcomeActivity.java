package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.github.sadjz.R;

/**
 * The type Welcome activity.
 */
@SuppressWarnings("CyclicClassDependency")
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    /**
     * On login pressed.
     *
     * @param view the view
     */
    public void onLoginPressed(View view) {
        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);
    }

    /**
     * On register pressed.
     * @param view Activity view
     */
    @SuppressWarnings("unused")
    public void onRegisterPressed(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);

        startActivity(intent);
    }
}
