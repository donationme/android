package com.github.sadjz.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import com.github.sadjz.R;
import com.github.sadjz.consts.MessageIdentifier;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.SearchManager;
import com.github.sadjz.models.donationItem.DonationItemModel;
import com.github.sadjz.models.donationItem.ItemCategory;
import com.github.sadjz.models.location.LocationModel;
import com.github.sadjz.models.message.MessageModel;
import com.github.sadjz.models.search.SearchModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Search activity.
 */

@SuppressWarnings({"ClassWithTooManyDependencies", "CyclicClassDependency"})
public class SearchActivity extends Activity
        implements ListAdapter.ItemClickListener {

    private EditText searchTextField;
    private ListAdapter itemAdapter;
    private SearchActivity currentActivity;
    private TextView currentlySelectedText;
    private Button selectBtn;
    private RadioButton categoryRadioBtn;
    private List<DonationItemModel> donationItems;
    private String currentLocationId = "";
    private boolean isSearchingAll = true;
    private Spinner categorySpinner;

    @SuppressWarnings("FeatureEnvy")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchTextField = findViewById(R.id.searchTextField);
        selectBtn = findViewById(R.id.selectBtn);
        currentlySelectedText = findViewById(R.id.currentlySelectedText);
        RecyclerView itemRecyclerView = findViewById(R.id.items);
        categoryRadioBtn = findViewById(R.id.categoryRadioBtn);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ListAdapter(this);
        itemAdapter.setClickListener(this);
        itemRecyclerView.setAdapter(itemAdapter);
        categorySpinner = findViewById(R.id.categorySpinnerSearch);

        ArrayAdapter<ItemCategory> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        ItemCategory.values());
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        RadioGroup searchRadioGroup = findViewById(R.id.searchRadioGroup);
        categorySpinner.setVisibility(View.GONE);
        categorySpinner.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(
                            AdapterView<?> parentView,
                            View selectedItemView,
                            int position,
                            long id) {
                        ItemCategory[] itemCategories = ItemCategory.values();
                        onSearch(String.valueOf(itemCategories[position]));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {}
                });

        searchRadioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(
                            RadioGroup group, int checkedId) {
                        RadioButton rb = findViewById(checkedId);
                        Object tag = rb.getTag();
                        String selectedSearch = tag.toString();

                        if ("name".equals(selectedSearch)) {
                            categorySpinner.setVisibility(View.GONE);
                            searchTextField.setVisibility(View.VISIBLE);
                            itemAdapter.updateList(new ArrayList<String>());

                        } else if ("category".equals(selectedSearch)) {
                            categorySpinner.setVisibility(View.VISIBLE);
                            searchTextField.setVisibility(View.GONE);
                            categorySpinner.setSelection(
                                    ItemCategory.Other.ordinal());
                            itemAdapter.updateList(new ArrayList<String>());
                            onSearch(
                                    String.valueOf(
                                            ItemCategory.Other.ordinal()));
                        }
                    }
                });

        searchTextField.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void beforeTextChanged(
                            CharSequence s, int start, int count, int after) {}

                    @Override
                    public void afterTextChanged(Editable s) {}

                    @Override
                    public void onTextChanged(
                            CharSequence s, int start, int before, int count) {
                        onSearch(s.toString());
                    }
                });
    }

    /**
     * On select location option.
     *
     * @param view the view
     */
    public void onSelectLocationOption(View view) {
        if (!this.isSearchingAll) {
            this.currentLocationId = "";
            this.isSearchingAll = true;
            this.currentlySelectedText.setText("");
            this.selectBtn.setText(R.string.selectSpecificLocation);
            this.currentlySelectedText.setText(R.string.searchingAllLocations);

        } else {
            this.currentLocationId = "";
            Intent intent = new Intent(this, LocationListActivity.class);
            intent.putExtra(
                    MessageIdentifier.Message.getMessageIdentifier(),
                    new MessageModel(true));
            startActivityForResult(intent, 1);
        }
    }

    @SuppressWarnings("FeatureEnvy")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                LocationModel location =
                        data.getParcelableExtra(
                                MessageIdentifier.Location
                                        .getMessageIdentifier());
                if (location != null) {
                    this.isSearchingAll = false;
                    this.donationItems = location.getDonationItems();
                    this.selectBtn.setText(R.string.searchingAllLocations);
                    this.currentLocationId = location.getId();
                    this.currentlySelectedText.setText(
                            String.format(
                                    "Searching : %s", location.getName()));

                } else {
                    this.isSearchingAll = true;
                }
            }
        }
    }
    /**
     * On Search
     * @param query Search Query
     */
    @SuppressWarnings("FeatureEnvy")
    private void onSearch(String query) {

        final SearchManager searchManager = new SearchManager();

        this.currentActivity = this;

        RestCallback<SearchModel> searchCallback =
                new RestCallback<SearchModel>() {
                    @SuppressWarnings("FeatureEnvy")
                    @Override
                    public void invokeSuccess(final SearchModel model) {

                        if (model.getResults() != null) {
                            donationItems = model.getResults();

                            Looper looper = Looper.getMainLooper();
                            Handler handler = new Handler(looper);
                            handler.post(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                                    updateItems(
                                                            model.getResults());
                                                }
                                            });
                        } else {

                            Window window = currentActivity
                                    .getWindow();
                            View view = window.getDecorView();
                            Snackbar snackbar = Snackbar.make(
                                            view.getRootView(),
                                            "No Results",
                                            Snackbar.LENGTH_LONG);
                            snackbar.setAction("Action", null);
                            snackbar.show();
                        }
                    }

                    @Override
                    public void invokeFailure() {


                        Window window = currentActivity
                                .getWindow();
                        View view = window.getDecorView();
                        Snackbar snackbar = Snackbar.make(
                                view.getRootView(),
                                "Please enter valid donation item",
                                Snackbar.LENGTH_LONG);
                        snackbar.setAction("Action", null);
                        snackbar.show();

                    }
                };
        if (!query.isEmpty()) {

            if (this.isSearchingAll) {
                if (this.categoryRadioBtn.isChecked()) {
                    searchManager.searchAllCategory(
                            HomeActivity.getTokenModel(), query, searchCallback);

                } else {
                    searchManager.searchAllName(
                            HomeActivity.getTokenModel(), query, searchCallback);
                }
            } else {

                if (this.categoryRadioBtn.isChecked()) {
                    searchManager.searchSpecificCategory(
                            HomeActivity.getTokenModel(),
                            query,
                            searchCallback,
                            this.currentLocationId);

                } else {
                    searchManager.searchSpecificName(
                            HomeActivity.getTokenModel(),
                            query,
                            searchCallback,
                            this.currentLocationId);
                }
            }
        } else {
            searchManager.abortSearch();
            itemAdapter.updateList(new ArrayList<String>());
        }
    }

    private void updateItems(Iterable<DonationItemModel> donationItems) {
        Collection<String> donationItemNames = new ArrayList<>();
        for (DonationItemModel item : donationItems) {
            donationItemNames.add(item.getName());
        }

        itemAdapter.updateList(donationItemNames);

        if (donationItemNames.isEmpty()) {
            Snackbar snackbar = Snackbar.make(
                            findViewById(R.id.searchRoot),
                            "No Results",
                            Snackbar.LENGTH_LONG);
            snackbar.setAction("Action", null);
            snackbar.show();
        }
    }

    @Override
    public void onItemClick(int position) {

        DonationItemModel donationItemModel = donationItems.get(position);

        Intent intent = new Intent(currentActivity, ItemDetailsActivity.class);
        intent.putExtra(
                MessageIdentifier.DonationItem.getMessageIdentifier(),
                donationItemModel);

        startActivity(intent);
    }
}
