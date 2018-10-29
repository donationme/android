package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.sadjz.R;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.LocationManager;
import com.github.sadjz.models.location.LocationModel;
import com.github.sadjz.models.location.RegionModel;

import java.util.ArrayList;
import java.util.List;

public class LocationListActivity extends AppCompatActivity implements ListAdapter.ItemClickListener {

    private RecyclerView locRecyclerView;
    private ListAdapter locAdapter;
    private RegionModel region;
    LocationListActivity currentActivity;




    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        locRecyclerView = findViewById(R.id.locations);
        locRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        locAdapter = new ListAdapter(this);
        locAdapter.setClickListener(this);
        locRecyclerView.setAdapter(locAdapter);


        //Put this in Location Controller


        final LocationManager locationManager = new LocationManager();


        RestCallback<RegionModel> locationCallback = new RestCallback<RegionModel>() {
            @Override
            public void invokeSuccess(RegionModel model) {

                try {


                    region = model;

                    new Handler(Looper.getMainLooper()).post(new Runnable(){
                        @Override
                        public void run() {
                            List<LocationModel> locations = region.getLocations();
                            ArrayList<String> locationNames = new ArrayList<String>();
                            for (LocationModel location : locations) {
                                locationNames.add(location.getName());
                            }

                            locAdapter.updateList(locationNames);
                        }
                    });

                }catch(Exception e){
                    System.out.print(e);
                }



            }

            @Override
            public void invokeFailure(){

            }
        };


        locationManager.getLocations(HomeActivity.tokenModel, locationCallback);
        currentActivity = this;




    }

    @Override
    public void onItemClick(View view, int position) {



        LocationModel locationListObject =  region.getLocations().get(position);

        Intent intent = new Intent(currentActivity, LocationDetailsActivity.class);
        intent.putExtra("locationData", (Parcelable) locationListObject);

        startActivity(intent);

    }

}
