package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.github.sadjz.R;
import com.github.sadjz.models.login.TokenModel;
import com.github.sadjz.models.user.UserModel;
import com.github.sadjz.models.user.UserType;

import java.util.Objects;

/**
 * The type Home activity.
 */
@SuppressWarnings({"CyclicClassDependency", "unused"})
public class HomeActivity extends AppCompatActivity {

    /**
     * The constant userModel.
     */
    @Nullable
    private static UserModel userModel;
    /**
     * The constant tokenModel.
     */
    @Nullable
    private static TokenModel tokenModel;

    /**
     * Gets app user model
     * @return User model
     */
    @Nullable
    public static UserModel getUserModel(){
        return HomeActivity.userModel;
    }

    /**
     * Gets app user token
     * @return User token
     */
    @Nullable
    public static TokenModel getTokenModel(){
        return HomeActivity.tokenModel;
    }

    /**
     * Sets user model for whole app
     * @param userModel User model to set
     */
    public static void setUserModel(@Nullable UserModel userModel){
        HomeActivity.userModel = userModel;
    }

    /**
     * Sets token model for whole app
     * @param tokenModel Token model to set
     */
    public static void setTokenModel(@Nullable TokenModel tokenModel){
        HomeActivity.tokenModel = tokenModel;
    }




    @SuppressWarnings("FeatureEnvy")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView typeLabel = findViewById(R.id.typeLabel);
        TextView emailLabel = findViewById(R.id.emailLabel);
        TextView nameLabel = findViewById(R.id.nameLabel);
        Button mapDataBtn = findViewById(R.id.mapDataBtn);
        UserModel userModel = Objects.requireNonNull(HomeActivity.userModel);

        UserType type = userModel.getType();
        typeLabel.setText(type.name());
        emailLabel.setText(userModel.getEmail());
        nameLabel.setText(userModel.getName());
        mapDataBtn.setText("Map Data");
    }

    /**
     * On logout pressed.
     *
     */
    @SuppressWarnings("unused")
    public void onLogoutPressed() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        finishAffinity();
        startActivity(intent);
        HomeActivity.setUserModel(null);
        HomeActivity.setUserModel(null);
    }

    /**
     * On map data pressed.
     *
     * @param view the view
     */
    @SuppressWarnings("unused")
    public void onMapDataPressed(@SuppressWarnings("unused") View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    /**
     * On search pressed.
     *
     */
    public void onSearchPressed(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

}
