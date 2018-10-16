package com.github.sadjz.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.github.sadjz.models.location.LocationListObject;

import com.github.sadjz.R;


public class LocationDetails extends AppCompatActivity {

    private TextView nameTextField;
    private TextView addressTextField;
    private TextView typeTextField;
    private TextView phoneTextField;
    private TextView websiteTextField;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc_detail);

        nameTextField = findViewById(R.id.nameTextField);
        addressTextField = findViewById(R.id.addressTextField);
        typeTextField = findViewById(R.id.typeTextField);
        phoneTextField = findViewById(R.id.phoneTextField);
        websiteTextField = findViewById(R.id.websiteTextField);

        LocationListObject location = getIntent().getParcelableExtra("locationData");

        nameTextField.setText(location.getName());
        addressTextField.setText(location.getAddress());
        typeTextField.setText(location.getType());
        phoneTextField.setText(location.getPhone());
        websiteTextField.setText(location.getWebsite());

    }
}
