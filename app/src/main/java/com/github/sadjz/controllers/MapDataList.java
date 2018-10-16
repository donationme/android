package com.github.sadjz.controllers;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.sadjz.R;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.LocationManager;
import com.github.sadjz.models.location.LocationListObject;
import com.github.sadjz.models.location.LocationModel;

import java.util.ArrayList;
import java.util.List;

public class MapDataList extends AppCompatActivity implements LocationAdapter.ItemClickListener {

    private RecyclerView locRecyclerView;
    private LocationAdapter locAdapter;
    private List<LocationListObject> locations;





    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_data_list);

        locRecyclerView = findViewById(R.id.locations);
        locRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        locAdapter = new LocationAdapter(this);
        locAdapter.setClickListener(this);
        locRecyclerView.setAdapter(locAdapter);


        //Put this in Location Controller


        final LocationManager locationManager = new LocationManager();


        RestCallback<LocationModel> locationCallback = new RestCallback<LocationModel>() {
            @Override
            public void invokeSuccess(LocationModel model) {


                locations =  model.getLocations();
                ArrayList<String> locationNames = new ArrayList<String>();
                for (LocationListObject location : locations) {
                    locationNames.add(location.getName());
                }

                locAdapter.setData(locationNames);




            }

            @Override
            public void invokeFailure(){

            }
        };


        locationManager.getLocations(Home.tokenModel, locationCallback);





    }

    @Override
    public void onItemClick(View view, int position) {
        
        LocationListObject locationListObject =  locations.get(position);
        System.out.print(locationListObject.getName());

        Intent intent = new Intent(this, LocationDetails.class);
        intent.putExtra("locationData", (Parcelable) locationListObject);

        startActivity(intent);
    }

}
