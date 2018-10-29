package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.github.sadjz.R;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.DonationItemManager;
import com.github.sadjz.models.account.ServerResponse;
import com.github.sadjz.models.donationItem.DonationItemModel;
import com.github.sadjz.models.donationItem.ItemCategory;

public class ItemAddActivity extends AppCompatActivity {

    private EditText descriptionText;
    private EditText nameText;
    private EditText quantityText;
    private EditText categoryText;
    final DonationItemManager donationItemManager = new DonationItemManager();
    DonationItemModel item;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        item = getIntent().getParcelableExtra("itemData");
        descriptionText = findViewById(R.id.descriptionText);
        nameText = findViewById(R.id.nameText);
        quantityText = findViewById(R.id.quantityText);
        categoryText = findViewById(R.id.categoryText);


        descriptionText.setText(item.getDescription());
        nameText.setText(item.getName());
        quantityText.setText( String.valueOf(item.getQuantity()));
        categoryText.setText( item.getCategory().name());

    }


    public void onAddItemPress(final View view) {


        final ItemAddActivity currentActivity = this;

        DonationItemModel donationItemModel = new DonationItemModel(nameText.getText().toString(),
                                                                    descriptionText.getText().toString(),
                                                                    Integer.parseInt(quantityText.getText().toString()),
                                                                    ItemCategory.valueOf(categoryText.getText().toString()), item.getTime(), "", item.getLocationid());


        RestCallback<ServerResponse[]> donationItemCallback = new RestCallback<ServerResponse[]>() {
            @Override
            public void invokeSuccess(ServerResponse[] model) {
                if (model.length == 0){
                    currentActivity.finish();

                }else{
                    Snackbar.make(view, model[0].getErrorMessage(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }

            @Override
            public void invokeFailure(){
                Snackbar.make(view, "Please enter valid donation item", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        };


        donationItemManager.addDonationItem(HomeActivity.tokenModel, donationItemModel, donationItemCallback);



    }





}
