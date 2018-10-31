package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.github.sadjz.R;
import com.github.sadjz.consts.MessageIdentifier;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.DonationItemManager;
import com.github.sadjz.models.account.ServerResponse;
import com.github.sadjz.models.donationItem.DonationItemModel;
import com.github.sadjz.models.donationItem.ItemCategory;
import com.github.sadjz.models.user.UserType;

public class ItemAddActivity extends AppCompatActivity {

    private EditText descriptionText;
    private EditText nameText;
    private EditText quantityText;
    final DonationItemManager donationItemManager = new DonationItemManager();
    DonationItemModel item;
    private Spinner categorySpinner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        item = getIntent().getParcelableExtra(MessageIdentifier.DonationItem.getMessageIdentifier());
        descriptionText = findViewById(R.id.descriptionText);
        nameText = findViewById(R.id.nameText);
        quantityText = findViewById(R.id.quantityText);
        categorySpinner = findViewById(R.id.categorySpinnerAdd);


        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, ItemCategory.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        //Sets to user type
        categorySpinner.setSelection(ItemCategory.Other.ordinal());



        descriptionText.setText(item.getDescription());
        nameText.setText(item.getName());
        quantityText.setText( String.valueOf(item.getQuantity()));

    }


    public void onAddItemPress(final View view) {



        this.item = new DonationItemModel(nameText.getText().toString(),
                                                                    descriptionText.getText().toString(),
                                                                    Integer.parseInt(quantityText.getText().toString()),
                                                                    (ItemCategory) categorySpinner.getSelectedItem(), item.getTime(), "", item.getLocationid());
        final ItemAddActivity currentActivity = this;


        RestCallback<ServerResponse[]> donationItemCallback = new RestCallback<ServerResponse[]>() {
            @Override
            public void invokeSuccess(ServerResponse[] model) {
                if (model.length == 0){

                    Intent intent = new Intent();
                    intent.putExtra(MessageIdentifier.DonationAddItem.getMessageIdentifier(), currentActivity.item);
                    setResult(MessageIdentifier.DonationAddItem.ordinal(), intent);
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


        donationItemManager.addDonationItem(HomeActivity.tokenModel, this.item, donationItemCallback);



    }





}
