package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.github.sadjz.R;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.SearchManager;
import com.github.sadjz.models.donationItem.DonationItemModel;
import com.github.sadjz.models.search.SearchModel;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends AppCompatActivity implements ListAdapter.ItemClickListener {

    private EditText searchTextField;
    private SearchModel searchModel;
    private RecyclerView itemRecyclerView;
    private ListAdapter itemAdapter;
    private SearchActivity currentActivity;
    private RadioButton nameRadioBtn;
    private RadioButton categoryRadioBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchTextField = findViewById(R.id.searchTextField);

        itemRecyclerView = findViewById(R.id.items);
        nameRadioBtn = findViewById(R.id.nameRadioBtn);
        categoryRadioBtn = findViewById(R.id.categoryRadioBtn);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ListAdapter(this);
        itemAdapter.setClickListener(this);
        itemRecyclerView.setAdapter(itemAdapter);



        searchTextField.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) { }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                onSearch(s.toString());

            }
        });


    }

    public void onSearch(String query) {

        final SearchManager searchManager = new SearchManager();

        this.currentActivity = this;


        RestCallback<SearchModel> searchCallback = new RestCallback<SearchModel>() {
            @Override
            public void invokeSuccess(SearchModel model) {


                if (model.getResults() != null){
                    searchModel = model;

                    new Handler(Looper.getMainLooper()).post(new Runnable(){
                        @Override
                        public void run() {
                            List<DonationItemModel> items = searchModel.getResults();
                            ArrayList<String> donationItemNames = new ArrayList<String>();
                            for (DonationItemModel item : items) {
                                donationItemNames.add(item.getName());
                            }

                            itemAdapter.updateList(donationItemNames);
                            if (donationItemNames.size() == 0){
                                Snackbar.make(currentActivity.getWindow().getDecorView().getRootView(), "No Results", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                            }
                        }
                    });
                }else{
                    Snackbar.make(currentActivity.getWindow().getDecorView().getRootView(), "No Results", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                }




            }

            @Override
            public void invokeFailure(){
                Snackbar.make(currentActivity.getWindow().getDecorView().getRootView(), "Please enter valid donation item", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        };

        if(query.length() > 0){
            if (this.categoryRadioBtn.isChecked()){
                searchManager.searchCategory(HomeActivity.tokenModel, query, searchCallback);

            }else{
                searchManager.searchNameQuery(HomeActivity.tokenModel, query, searchCallback);

            }
        }else{
            searchManager.abortSearch();
            itemAdapter.updateList(new ArrayList<String>());
        }

    }

    @Override
    public void onItemClick(View view, int position) {



        DonationItemModel donationItemModel =  searchModel.getResults().get(position);

        Intent intent = new Intent(currentActivity, ItemDetailsActivity.class);
        intent.putExtra("itemData", (Parcelable) donationItemModel);

        startActivity(intent);

    }

}
