package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.github.sadjz.R;

import com.github.sadjz.consts.MessageIdentifier;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.DonationItemManager;
import com.github.sadjz.models.donationItem.DonationItemModel;
import com.github.sadjz.models.donationItem.ItemCategory;
import com.github.sadjz.models.user.UserModel;
import com.github.sadjz.models.user.UserType;
import com.google.gson.internal.LinkedTreeMap;

import java.util.Collection;
import java.util.Objects;

/**
 * The type Item details activity.
 */

@SuppressWarnings("CyclicClassDependency")
public class ItemDetailsActivity extends AppCompatActivity {

    private EditText descriptionText;
    private EditText nameText;
    private EditText quantityText;
    private Spinner categorySpinner;

    private DonationItemModel item;
    private final DonationItemManager donationItemManager = new DonationItemManager();

    @SuppressWarnings({"FeatureEnvy", "OverlyLongMethod"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Intent intent = getIntent();
        item =
                intent
                        .getParcelableExtra(
                                MessageIdentifier.DonationItem
                                        .getMessageIdentifier());

        TextView timeText = findViewById(R.id.timeText);
        descriptionText = findViewById(R.id.descriptionText);
        nameText = findViewById(R.id.nameText);
        quantityText = findViewById(R.id.quantityText);

        EditText quantityText = findViewById(R.id.quantityText);
        Button editButton = findViewById(R.id.editButton);
        Button removeButton = findViewById(R.id.removeButton);
        categorySpinner = findViewById(R.id.categorySpinnerDetail);

        ArrayAdapter<ItemCategory> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        ItemCategory.values());
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        ItemCategory category = item.getCategory();
        categorySpinner.setSelection(category.ordinal());
        UserModel userModel = Objects.requireNonNull(HomeActivity.getUserModel());
        if (userModel != null) {
            @SuppressWarnings("LawOfDemeter") UserType userType = userModel.getType();
            if (!((userType == UserType.Admin))
                    || (userType.equals(UserType.LocationEmployee))) {
                descriptionText.setEnabled(false);
                nameText.setEnabled(false);
                quantityText.setEnabled(false);
                categorySpinner.setEnabled(false);
                editButton.setVisibility(View.GONE);
                removeButton.setVisibility(View.GONE);
            }

        }

        timeText.setText(item.getTime());
        descriptionText.setText(item.getDescription());
        nameText.setText(item.getName());
        quantityText.setText(String.valueOf(item.getQuantity()));
    }

    /**
     * On edit item press.
     *
     * @param view the view
     */
    @SuppressWarnings("FeatureEnvy")
    public void onEditItemPress(final View view) {
        Editable nameEditable = nameText.getText();
        Editable descriptionEditable = descriptionText.getText();
        Editable quantityEditable = quantityText.getText();

        this.item =
                new DonationItemModel(
                        nameEditable.toString(),
                        descriptionEditable.toString(),
                        Integer.parseInt(quantityEditable.toString()),
                        (ItemCategory) categorySpinner.getSelectedItem(),
                        item.getTime(),
                        item.getID(),
                        item.getLocationId());

        final ItemDetailsActivity currentActivity = this;

        RestCallback<LinkedTreeMap> donationItemCallback =
                new RestCallback<LinkedTreeMap>() {
                    @Override
                    public void invokeSuccess(LinkedTreeMap model) {

                        Intent intent = new Intent();
                        intent.putExtra(
                                MessageIdentifier.DonationEditItem
                                        .getMessageIdentifier(),
                                currentActivity.item);
                        setResult(
                                MessageIdentifier.DonationEditItem.ordinal(),
                                intent);
                        currentActivity.finish();
                    }

                    @Override
                    public void invokeFailure() {
                        Snackbar snackbar = Snackbar.make(
                                        view,
                                        "Please enter valid donation item",
                                        Snackbar.LENGTH_LONG);
                        snackbar.setAction("Action", null);
                        snackbar.show();
                    }
                };

        donationItemManager.editDonationItem(
                HomeActivity.getTokenModel(), item, donationItemCallback);
    }

    /**
     * On remove item press.
     *
     * @param view the view
     */
    public void onRemoveItemPress(final View view) {

        final ItemDetailsActivity currentActivity = this;

        RestCallback<LinkedTreeMap> donationItemCallback =
                new RestCallback<LinkedTreeMap>() {
                    @Override
                    public void invokeSuccess(LinkedTreeMap model) {
                        if (model.isEmpty()) {

                            Intent intent = new Intent();
                            intent.putExtra(
                                    MessageIdentifier.DonationRemoveItem
                                            .getMessageIdentifier(),
                                    currentActivity.item);
                            setResult(
                                    MessageIdentifier.DonationRemoveItem
                                            .ordinal(),
                                    intent);
                            currentActivity.finish();

                        } else {
                            Collection registerErrorCollection = model.values();
                            String registerErrorString = registerErrorCollection
                                    .toArray()[0].toString();
                            Snackbar snackbar = Snackbar.make(
                                            view,
                                            registerErrorString,
                                            Snackbar.LENGTH_LONG);
                            snackbar.setAction("Action", null);
                            snackbar.show();
                        }
                    }

                    @Override
                    public void invokeFailure() {
                        Snackbar snackbar = Snackbar.make(
                                        view,
                                        "Please enter valid donation item",
                                        Snackbar.LENGTH_LONG);
                        snackbar.setAction("Action", null);
                        snackbar.show();
                    }
                };

        donationItemManager.removeDonationItem(
                HomeActivity.getTokenModel(), item, donationItemCallback);
    }
}
