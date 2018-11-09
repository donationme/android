package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.github.sadjz.R;
import com.github.sadjz.consts.MessageIdentifier;
import com.github.sadjz.models.donationItem.DonationItemModel;
import com.github.sadjz.models.donationItem.ItemCategory;
import com.github.sadjz.models.location.LocationModel;
import com.github.sadjz.models.user.UserType;
import java.util.ArrayList;
import java.util.List;

public class LocationDetailsActivity extends AppCompatActivity
        implements ListAdapter.ItemClickListener {

    private TextView nameTextField;
    private TextView addressTextField;
    private TextView typeTextField;
    private TextView phoneTextField;
    private TextView websiteTextField;

    private RecyclerView itemRecyclerView;
    private ListAdapter itemAdapter;
    private List<DonationItemModel> items;
    private LocationModel location;
    private Button addItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        nameTextField = findViewById(R.id.nameTextField);
        addressTextField = findViewById(R.id.addressTextField);
        typeTextField = findViewById(R.id.typeTextField);
        phoneTextField = findViewById(R.id.quantityTextField);
        websiteTextField = findViewById(R.id.descriptionTextField);
        addItemButton = findViewById(R.id.addItemButton);
        location =
                getIntent()
                        .getParcelableExtra(
                                MessageIdentifier.Location
                                        .getMessageIdentifier());

        nameTextField.setText(location.getName());
        addressTextField.setText(location.getAddress());
        typeTextField.setText(location.getType());
        phoneTextField.setText(location.getPhone());
        websiteTextField.setText(location.getWebsite());

        if (!((HomeActivity.userModel.getType() == UserType.Admin)
                || (HomeActivity.userModel.getType()
                == UserType.LocationEmployee))) {
            addItemButton.setVisibility(View.GONE);
        }

        itemRecyclerView = findViewById(R.id.items);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ListAdapter(this);
        itemAdapter.setClickListener(this);
        itemRecyclerView.setAdapter(itemAdapter);
        this.items = location.getDonationItems();
        this.updateList(items);
    }

    private void updateList(List<DonationItemModel> items) {
        List<String> itemNames = new ArrayList<>();
        for (DonationItemModel donationItem : items) {
            itemNames.add(donationItem.getName());
        }
        itemAdapter.updateList(itemNames);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();
        intent.putExtra(
                MessageIdentifier.Location.getMessageIdentifier(),
                this.location);
        setResult(RESULT_OK, intent);
        this.finish();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<DonationItemModel> oldItems = this.location.getDonationItems();

        if (requestCode == 1) {
            if (resultCode == MessageIdentifier.DonationEditItem.ordinal()) {

                DonationItemModel editedItem =
                        data.getParcelableExtra(
                                MessageIdentifier.DonationEditItem
                                        .getMessageIdentifier());

                if (editedItem != null) {
                    String newID = editedItem.getID();

                    for (int i = 0; i < oldItems.size(); i++) {
                        String oldID = oldItems.get(i).getID();
                        if (newID.equals(oldID)) {
                            oldItems.set(i, editedItem);
                            break;
                        }
                    }
                    this.location.setDonationItems(oldItems);
                    this.updateList(oldItems);
                }

            } else if (resultCode
                    == MessageIdentifier.DonationRemoveItem.ordinal()) {

                DonationItemModel removedItem =
                        data.getParcelableExtra(
                                MessageIdentifier.DonationRemoveItem
                                        .getMessageIdentifier());
                if (removedItem != null) {

                    String newID = removedItem.getID();

                    for (int i = 0; i < oldItems.size(); i++) {
                        String oldID = oldItems.get(i).getID();
                        if (newID.equals(oldID)) {
                            oldItems.remove(i);
                            break;
                        }
                    }
                    this.location.setDonationItems(oldItems);
                    this.updateList(oldItems);
                }
            }
        } else if (requestCode == 2) {
            if (resultCode == MessageIdentifier.DonationAddItem.ordinal()) {
                DonationItemModel newItem =
                        data.getParcelableExtra(
                                MessageIdentifier.DonationAddItem
                                        .getMessageIdentifier());
                if (newItem != null) {
                    oldItems.add(newItem);
                    this.location.setDonationItems(oldItems);
                    this.updateList(oldItems);
                }
            }
        }
    }

    public void onAddItemPress(final View view) {

        DonationItemModel donationItemListObject =
                new DonationItemModel(
                        "",
                        "",
                        0,
                        ItemCategory.Other,
                        "2018-10-24T03:06:42.219Z",
                        "",
                        location.getId());

        Intent intent = new Intent(this, ItemAddActivity.class);
        intent.putExtra(
                MessageIdentifier.DonationItem.getMessageIdentifier(),
                donationItemListObject);

        startActivityForResult(intent, 2);
    }

    @Override
    public void onItemClick(int position) {

        DonationItemModel donationItemListObject = items.get(position);

        Intent intent = new Intent(this, ItemDetailsActivity.class);
        intent.putExtra(
                MessageIdentifier.DonationItem.getMessageIdentifier(),
                donationItemListObject);

        startActivityForResult(intent, 1);
    }
}
