package com.github.sadjz.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.github.sadjz.models.donationItem.DonationItemListObject;

import com.github.sadjz.R;

public class ItemDetails extends AppCompatActivity {

    private TextView timeStampTextField;
    private TextView locationTextField;
    private TextView shortDescriptionTextField;
    private TextView longDescriptionTextField;
    private TextView valueTextField;
    private TextView categoryTextField;
    private TextView commentsTextField;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        timeStampTextField = findViewById(R.id.timeStampTextField);
        locationTextField = findViewById(R.id.locationTextField);
        shortDescriptionTextField = findViewById(R.id.shortDescriptionTextField);
        longDescriptionTextField = findViewById(R.id.longDescriptionTextField);
        valueTextField = findViewById(R.id.valueTextField);
        categoryTextField = findViewById(R.id.categoryTextField);
        commentsTextField = findViewById(R.id.commentsTextField);

        DonationItemListObject item = getIntent().getParcelableExtra("itemData");

        timeStampTextField.setText(item.getTimeStamp());
        locationTextField.setText(item.getLocation());
        shortDescriptionTextField.setText(item.getShortDescription());
        longDescriptionTextField.setText(item.getLongDescription());
        valueTextField.setText(Long.toString(item.getValue()));
        categoryTextField.setText(item.getCategory());
        commentsTextField.setText(item.getComments());
    }
}
