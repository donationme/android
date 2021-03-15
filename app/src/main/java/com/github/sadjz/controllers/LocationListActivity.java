package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.sadjz.R;
import com.github.sadjz.consts.MessageIdentifier;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.LocationManager;
import com.github.sadjz.models.location.LocationModel;
import com.github.sadjz.models.location.RegionModel;
import com.github.sadjz.models.message.MessageModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Location list activity.
 */
@SuppressWarnings({"ClassWithTooManyDependencies", "CyclicClassDependency"})
public class LocationListActivity extends AppCompatActivity
        implements ListAdapter.ItemClickListener {

    private ListAdapter locAdapter;
    private RegionModel region;
    private LocationListActivity currentActivity;
    private boolean isForSearch;
    private final LocationManager locationManager = new LocationManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        RecyclerView locRecyclerView = findViewById(R.id.locations);
        locRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        locAdapter = new ListAdapter(this);
        locAdapter.setClickListener(this);
        locRecyclerView.setAdapter(locAdapter);
        Intent intent = getIntent();
        // Put this in Region Controller
        MessageModel item =
                intent.getParcelableExtra(
                                MessageIdentifier.Message
                                        .getMessageIdentifier());
        if (item != null) {
            this.isForSearch = item.getState();
        }
        this.updateLocations();
    }

    private void updateLocations() {

        RestCallback<RegionModel> locationCallback =
                new RestCallback<RegionModel>() {
                    @Override
                    public void invokeSuccess(RegionModel model) {

                        try {

                            region = model;
                            Looper looper = Looper.getMainLooper();
                            Handler handler = new Handler(looper);
                            handler.post(
                                            new Runnable() {
                                                @Override
                                                public void run() {
                                                    List<LocationModel>
                                                            locations =
                                                                    region
                                                                            .getLocations();
                                                    Collection<String>
                                                            locationNames =
                                                            new ArrayList<>();
                                                    for (LocationModel
                                                            location :
                                                                    locations) {
                                                        locationNames.add(
                                                                location
                                                                        .getName());
                                                    }

                                                    locAdapter.updateList(
                                                            locationNames);
                                                }
                                            });

                        } catch (Exception ignored) {


                        }
                    }

                    @Override
                    public void invokeFailure() {}
                };

        locationManager.getLocations(HomeActivity.getTokenModel(), locationCallback);
        currentActivity = this;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                LocationModel locationModel =
                        data.getParcelableExtra(
                                MessageIdentifier.Location
                                        .getMessageIdentifier());
                if (locationModel != null) {

                    this.updateLocations();
                }
            }
        }
    }

    @Override
    public void onItemClick(int position) {
        List<LocationModel> locations = region.getLocations();
        LocationModel locationListObject = locations.get(position);

        if (this.isForSearch) {
            Intent intent = new Intent();
            intent.putExtra(
                    MessageIdentifier.Location.getMessageIdentifier(),
                    locationListObject);
            setResult(RESULT_OK, intent);
            currentActivity.finish();

        } else {

            Intent intent =
                    new Intent(currentActivity, LocationDetailsActivity.class);
            intent.putExtra(
                    MessageIdentifier.Location.getMessageIdentifier(),
                    locationListObject);
            startActivityForResult(intent, 1);
        }
    }
}
