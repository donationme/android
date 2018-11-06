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
import android.widget.TextView;

import com.github.sadjz.consts.MessageIdentifier;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.DonationItemManager;
import com.github.sadjz.models.donationItem.DonationItemModel;

import com.github.sadjz.R;
import com.github.sadjz.models.donationItem.ItemCategory;
import com.github.sadjz.models.user.UserType;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class ItemDetailsActivity extends AppCompatActivity {

    private TextView timeText;
    private EditText descriptionText;
    private EditText nameText;
    private EditText quantityText;
    private Button editButton;
    private Button removeButton;
    private Spinner categorySpinner;

    DonationItemModel item;
    final DonationItemManager donationItemManager = new DonationItemManager();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        item = getIntent().getParcelableExtra(MessageIdentifier.DonationItem.getMessageIdentifier());

        timeText = findViewById(R.id.timeText);
        descriptionText = findViewById(R.id.descriptionText);
        nameText = findViewById(R.id.nameText);
        quantityText = findViewById(R.id.quantityText);
        editButton = findViewById(R.id.editButton);
        removeButton = findViewById(R.id.removeButton);
        categorySpinner = findViewById(R.id.categorySpinnerDetail);


        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, ItemCategory.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setSelection(item.getCategory().ordinal());


        if (!(HomeActivity.userModel.getType() == UserType.Admin || HomeActivity.userModel.getType() == UserType.LocationEmployee)){
            descriptionText.setEnabled(false);
            nameText.setEnabled(false);
            quantityText.setEnabled(false);
            categorySpinner.setEnabled(false);
            editButton.setVisibility(View.GONE);
            removeButton.setVisibility(View.GONE);
        }




        timeText.setText(item.getTime());
        descriptionText.setText(item.getDescription());
        nameText.setText(item.getName());
        quantityText.setText( String.valueOf(item.getQuantity()));


    }


    public void onEditItemPress(final View view) {



        this.item = new DonationItemModel(nameText.getText().toString(),
                                                                    descriptionText.getText().toString(),
                                                                    Integer.parseInt(quantityText.getText().toString()),
                                                                    (ItemCategory) categorySpinner.getSelectedItem(),
                                                                    item.getTime(),item.getID(), item.getLocationId());

        final ItemDetailsActivity currentActivity = this;


        RestCallback<LinkedTreeMap> donationItemCallback = new RestCallback<LinkedTreeMap>() {
            @Override
            public void invokeSuccess(LinkedTreeMap model) {

                Intent intent = new Intent();
                intent.putExtra(MessageIdentifier.DonationEditItem.getMessageIdentifier(), currentActivity.item);
                setResult(MessageIdentifier.DonationEditItem.ordinal(), intent);
                currentActivity.finish();

            }

            @Override
            public void invokeFailure(){
                Snackbar.make(view, "Please enter valid donation item", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        };


        donationItemManager.editDonationItem(HomeActivity.tokenModel, item, donationItemCallback);



    }


    public void onRemoveItemPress(final View view) {


        final ItemDetailsActivity currentActivity = this;




        RestCallback<LinkedTreeMap> donationItemCallback = new RestCallback<LinkedTreeMap>() {
            @Override
            public void invokeSuccess(LinkedTreeMap model) {
                if (model.isEmpty()) {

                    Intent intent = new Intent();
                    intent.putExtra(MessageIdentifier.DonationRemoveItem.getMessageIdentifier(), currentActivity.item);
                    setResult(MessageIdentifier.DonationRemoveItem.ordinal(), intent);
                    currentActivity.finish();


                }else{
                    Snackbar.make(view, model.values().toArray()[0].toString(), Snackbar.LENGTH_LONG).setAction("Action", null).show();

                }
            }

            @Override
            public void invokeFailure(){
                Snackbar.make(view, "Please enter valid donation item", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        };


        donationItemManager.removeDonationItem(HomeActivity.tokenModel, item, donationItemCallback);



    }




}
