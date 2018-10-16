package com.github.sadjz.controllers;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.github.sadjz.models.location.LocationListObject;

import com.github.sadjz.R;


public class LocationDetails extends AppCompatActivity {

    private TextView key;
    private TextView name;
    private TextView latitude;
    private TextView longitude;
    private TextView street;
    private TextView city;
    private TextView state;
    private TextView zip;
    private TextView address;
    private TextView type;
    private TextView phone;
    private TextView website;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc_detail);

        key = findViewById(R.id.textView10);
        name = findViewById(R.id.textView11);
        latitude = findViewById(R.id.textView12);
        longitude = findViewById(R.id.textView13);
        street = findViewById(R.id.textView14);
        city = findViewById(R.id.textView15);
        state = findViewById(R.id.textView16);
        zip = findViewById(R.id.textView17);
        address = findViewById(R.id.textView18);
        type = findViewById(R.id.textView19);
        phone = findViewById(R.id.textView20);
        website = findViewById(R.id.textView21);

        LocationListObject location = getIntent().getParcelableExtra("locationData");

        key.setText((int) location.getKey());
        name.setText(location.getName());
        latitude.setText((int) location.getLatitude());
        longitude.setText((int) location.getLongitude());
        street.setText(location.getStreet());
        city.setText(location.getCity());
        state.setText(location.getState());
        zip.setText((int) location.getZip());
        address.setText(location.getAddress());
        type.setText(location.getType());
        phone.setText(location.getPhone());
    }
}
