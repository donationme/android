package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.github.sadjz.R;
import com.github.sadjz.consts.MessageIdentifier;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.LocationManager;
import com.github.sadjz.models.location.Coords;
import com.github.sadjz.models.location.LocationModel;
import com.github.sadjz.models.location.RegionModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;
import java.util.Objects;

/**
 * The type Maps activity.
 */

@SuppressWarnings("CyclicClassDependency")
public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private static final float MAP_ZOOM = 10.0f;

    private RegionModel region;
    private final LocationManager locationManager = new LocationManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready
        // to be used.
        FragmentManager fragmentManager =  getSupportFragmentManager();
        SupportMapFragment mapFragment =
                Objects.requireNonNull((SupportMapFragment) fragmentManager.
                findFragmentById(R.id.map));

        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        List<LocationModel> locations = this.region.getLocations();

        for (LocationModel location : locations) {
            String locationName = location.getName();

            if (locationName.equals(marker.getTitle())) {

                Intent intent = new Intent(this, LocationDetailsActivity.class);
                intent.putExtra(
                        MessageIdentifier.Location.getMessageIdentifier(),
                        location);
                startActivityForResult(intent, 1);

                return true;
            }
        }

        return false;
    }

    /**
     * Manipulates the map once available. This callback is triggered when the
     * map is ready to be used. This is where we can add markers or lines, add
     * listeners or move the camera. In this case, we just add a marker near
     * Sydney, Australia. If Google Play services is not installed on the
     * device, the user will be prompted to install it inside the
     * SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressWarnings("FeatureEnvy")
    @Override
    public void onMapReady(final GoogleMap googleMap) {

        googleMap.setOnMarkerClickListener(this);

        RestCallback<RegionModel> locationCallback =
                new RestCallback<RegionModel>() {
                    @SuppressWarnings("FeatureEnvy")
                    @Override
                    public void invokeSuccess(final RegionModel model) {

                        try {

                            Looper looper = Looper.getMainLooper();
                            Handler handler = new Handler(looper);
                            handler.post(
                                            new Runnable() {
                                                @SuppressWarnings({"FeatureEnvy", "unused"})
                                                @Override
                                                public void run() {
                                                    Coords regionCoords =
                                                            model.getCoords();

                                                    LatLng regionLatLng = new LatLng(
                                                            model.getLatitude(),
                                                            model.getLongitude());

                                                    googleMap.moveCamera(
                                                            CameraUpdateFactory
                                                                    .newLatLng(
                                                                            regionLatLng));
                                                    googleMap.animateCamera(
                                                            CameraUpdateFactory
                                                                    .zoomTo(
                                                                            MapsActivity.MAP_ZOOM));

                                                    region = model;
                                                    List<LocationModel>
                                                            locations =
                                                                    region.getLocations();
                                                    for (LocationModel
                                                            location :
                                                                    locations) {


                                                        LatLng donationCenter =
                                                                new LatLng(
                                                                        location.getLatitude(),
                                                                        location.getLongitude());

                                                        MarkerOptions markerOptions =
                                                                new MarkerOptions();
                                                        markerOptions.position(donationCenter);
                                                        markerOptions.title(location.getName());

                                                        googleMap.addMarker(markerOptions);
                                                    }
                                                }
                                            });

                        } catch (Exception ignored) {

                        }
                    }

                    @Override
                    public void invokeFailure() {}
                };

        locationManager.getLocations(HomeActivity.getTokenModel(), locationCallback);
    }
}
