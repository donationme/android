package com.github.sadjz.controllers;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.AccountManager;
import com.github.sadjz.managers.DonationItemManager;
import com.github.sadjz.models.account.ServerResponse;
import com.github.sadjz.models.donationItem.DonationItemModel;

import com.github.sadjz.R;
import com.github.sadjz.models.donationItem.ItemCategory;
import com.github.sadjz.models.user.UserModel;

public class ItemDetails extends AppCompatActivity {

    private TextView timeText;
    private EditText descriptionText;
    private EditText nameText;
    private EditText quantityText;
    private EditText categoryText;
    DonationItemModel item;
    final DonationItemManager donationItemManager = new DonationItemManager();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        timeText = findViewById(R.id.timeText);
        descriptionText = findViewById(R.id.descriptionText);
        nameText = findViewById(R.id.nameText);
        quantityText = findViewById(R.id.quantityText);
        categoryText = findViewById(R.id.categoryText);

        item = getIntent().getParcelableExtra("itemData");


        timeText.setText(item.getTime());
        descriptionText.setText(item.getDescription());
        nameText.setText(item.getName());
        quantityText.setText( String.valueOf(item.getQuantity()));
        categoryText.setText( item.getCategory().name());


    }


    public void onEditItemPress(final View view) {


        final ItemDetails currentActivity = this;

        DonationItemModel donationItemModel = new DonationItemModel(nameText.getText().toString(),
                                                                    descriptionText.getText().toString(),
                                                                    Integer.parseInt(quantityText.getText().toString()),
                                                                    ItemCategory.valueOf(categoryText.getText().toString()),
                item.getTime(),item.getID(), item.getLocationid());


        RestCallback<ServerResponse[]> donationItemCallback = new RestCallback<ServerResponse[]>() {
            @Override
            public void invokeSuccess(ServerResponse[] model) {

                Intent intent = new Intent(currentActivity, Home.class);
                startActivity(intent);
            }

            @Override
            public void invokeFailure(){
                Snackbar.make(view, "Please enter valid donation item", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        };


        donationItemManager.editDonationItem(Home.tokenModel, donationItemModel, donationItemCallback);



    }


    public void onRemoveItemPress(final View view) {


        final ItemDetails currentActivity = this;




        RestCallback<ServerResponse[]> donationItemCallback = new RestCallback<ServerResponse[]>() {
            @Override
            public void invokeSuccess(ServerResponse[] model) {

                Intent intent = new Intent(currentActivity, Home.class);
                startActivity(intent);
            }

            @Override
            public void invokeFailure(){
                Snackbar.make(view, "Please enter valid donation item", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        };


        donationItemManager.removeDonationItem(Home.tokenModel, item, donationItemCallback);



    }




}
