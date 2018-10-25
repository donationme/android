package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.sadjz.models.donationItem.DonationItemModel;
import com.github.sadjz.models.donationItem.ItemCategory;
import com.github.sadjz.models.location.LocationModel;
import android.support.v7.widget.RecyclerView;
import com.github.sadjz.R;
import com.github.sadjz.models.user.UserType;

import java.util.ArrayList;
import java.util.List;


public class LocationDetails extends AppCompatActivity implements ListAdapter.ItemClickListener{

    private TextView nameTextField;
    private TextView addressTextField;
    private TextView typeTextField;
    private TextView phoneTextField;
    private TextView websiteTextField;

    private RecyclerView itemRecyclerView;
    private ListAdapter itemAdapter;
    private List<DonationItemModel> items;
    LocationModel location;
    private Button addItemButton;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc_detail);

        nameTextField = findViewById(R.id.nameTextField);
        addressTextField = findViewById(R.id.addressTextField);
        typeTextField = findViewById(R.id.typeTextField);
        phoneTextField = findViewById(R.id.quantityTextField);
        websiteTextField = findViewById(R.id.descriptionTextField);
        addItemButton = findViewById(R.id.addItemButton);
        location = getIntent().getParcelableExtra("locationData");

        nameTextField.setText(location.getName());
        addressTextField.setText(location.getAddress());
        typeTextField.setText(location.getType());
        phoneTextField.setText(location.getPhone());
        websiteTextField.setText(location.getWebsite());




        if (!(Home.userModel.getType() == UserType.Admin || Home.userModel.getType() == UserType.LocationEmployee)){
            addItemButton.setVisibility(View.GONE);
        }


        itemRecyclerView = findViewById(R.id.items);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ListAdapter(this);
        itemAdapter.setClickListener(this);
        itemRecyclerView.setAdapter(itemAdapter);
        items =  location.getItems();
                ArrayList<String> itemNames = new ArrayList<String>();
                for (DonationItemModel donationItem : items) {
                    itemNames.add(donationItem.getName());
                }
        itemAdapter.updateList(itemNames);

    }



    public void onAddItemPress(final View view) {

        DonationItemModel donationItemListObject =  new DonationItemModel("","",0,ItemCategory.Other,"2018-10-24T03:06:42.219Z","",location.getId());

        Intent intent = new Intent(this, ItemAdd.class);
        intent.putExtra("itemData", donationItemListObject);

        startActivity(intent);


    }


        @Override
    public void onItemClick(View view, int position) {

        DonationItemModel donationItemListObject =  items.get(position);

        Intent intent = new Intent(this, ItemDetails.class);
        intent.putExtra("itemData", donationItemListObject);

        startActivity(intent);
    }
}
