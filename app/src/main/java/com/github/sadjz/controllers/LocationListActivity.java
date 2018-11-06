package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.sadjz.R;
import com.github.sadjz.consts.MessageIdentifier;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.LocationManager;
import com.github.sadjz.models.location.LocationModel;
import com.github.sadjz.models.location.RegionModel;
import com.github.sadjz.models.message.MessageModel;

import java.util.ArrayList;
import java.util.List;

public class LocationListActivity extends AppCompatActivity implements ListAdapter.ItemClickListener {

    private RecyclerView locRecyclerView;
    private ListAdapter locAdapter;
    private RegionModel region;
    LocationListActivity currentActivity;
    private boolean isForSearch = false;
    private final LocationManager locationManager = new LocationManager();



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        locRecyclerView = findViewById(R.id.locations);
        locRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        locAdapter = new ListAdapter(this);
        locAdapter.setClickListener(this);
        locRecyclerView.setAdapter(locAdapter);


        //Put this in Region Controller
        MessageModel item = getIntent().getParcelableExtra(MessageIdentifier.Message.getMessageIdentifier());
        if (item != null){
            this.isForSearch = item.getState();
        }
        this.updateLocations();





    }


    private void updateLocations(){

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



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                LocationModel locationModel = data.getParcelableExtra(MessageIdentifier.Location.getMessageIdentifier());
                if (locationModel != null){

                    this.updateLocations();

                }
            }
        }


    }



    @Override
    public void onItemClick(View view, int position) {
        LocationModel locationListObject =  region.getLocations().get(position);


        if (this.isForSearch){
            Intent intent = new Intent();
            intent.putExtra(MessageIdentifier.Location.getMessageIdentifier(), locationListObject);
            setResult(RESULT_OK, intent);
            currentActivity.finish();

        }else{

            Intent intent = new Intent(currentActivity, LocationDetailsActivity.class);
            intent.putExtra(MessageIdentifier.Location.getMessageIdentifier(), locationListObject);
            startActivityForResult(intent, 1);
        }

    }

}
