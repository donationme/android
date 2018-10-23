package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.ItemManager;
import com.github.sadjz.models.donationItem.DonationItemListObject;
import com.github.sadjz.models.donationItem.DonationItemModel;
import com.github.sadjz.models.location.LocationListObject;
import android.support.v7.widget.RecyclerView;
import com.github.sadjz.R;
import com.github.sadjz.models.user.UserType;

import java.util.ArrayList;
import java.util.List;


public class LocationDetails extends AppCompatActivity implements ItemAdapter.ItemClickListener{

    private TextView nameTextField;
    private TextView addressTextField;
    private TextView typeTextField;
    private TextView phoneTextField;
    private TextView websiteTextField;

    private RecyclerView itemRecyclerView;
    private ItemAdapter itemAdapter;
    private List<DonationItemListObject> items;

    private Button addItemButton;


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


        itemRecyclerView = findViewById(R.id.items);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter(this);
        itemAdapter.setClickListener(this);
        itemRecyclerView.setAdapter(itemAdapter);

        final ItemManager itemManager = new ItemManager();


        RestCallback<DonationItemModel> itemCallback = new RestCallback<DonationItemModel>() {
            @Override
            public void invokeSuccess(DonationItemModel model) {


                items =  model.getDonationItems();
                ArrayList<String> itemNames = new ArrayList<String>();
                for (DonationItemListObject donationItem : items) {
                    itemNames.add(donationItem.getShortDescription());
                }

                itemAdapter.setData(itemNames);

            }

            @Override
            public void invokeFailure(){

            }
        };


        itemManager.getItems(Home.tokenModel, itemCallback);

        if (Home.getUserModel().getType().equals(UserType.LocationEmployee)) {
            addItemButton = findViewById(R.id.addItemButton);
            final Intent newItemIntent = new Intent(this, NewItem.class);
            addItemButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(newItemIntent);
                }
            });
        }
    }

    @Override
    public void onItemClick(View view, int position) {

        DonationItemListObject donationItemListObject =  items.get(position);
        System.out.print(donationItemListObject.getShortDescription());

        Intent intent = new Intent(this, ItemDetails.class);
        intent.putExtra("itemData", donationItemListObject);

        startActivity(intent);
    }
}
