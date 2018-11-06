package com.github.sadjz.controllers;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private RegionModel region;
    private final LocationManager locationManager = new LocationManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }





    @Override
    public boolean onMarkerClick(Marker marker) {


            List<LocationModel> locations = this.region.getLocations();

            for (LocationModel location : locations){

                if (location.getName().equals(marker.getTitle())){

                    Intent intent = new Intent(this, LocationDetailsActivity.class);
                    intent.putExtra(MessageIdentifier.Location.getMessageIdentifier(), location);
                    startActivityForResult(intent, 1);

                    return true;

                }

            }


        return false;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {

        this.mMap = googleMap;

        googleMap.setOnMarkerClickListener(this);

        RestCallback<RegionModel> locationCallback = new RestCallback<RegionModel>() {
            @Override
            public void invokeSuccess(final RegionModel model) {

                try {

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                         @Override
                         public void run() {
                             Coords regionCoords = model.getCoords();

                             LatLng regionLatLng = new LatLng(regionCoords.getLatitude(), regionCoords.getLongitude());

                             googleMap.moveCamera(CameraUpdateFactory.newLatLng(regionLatLng));
                             googleMap.animateCamera( CameraUpdateFactory.zoomTo( 10.0f ) );

                             region = model;
                             List<LocationModel> locations = region.getLocations();
                             for (LocationModel location : locations) {

                                 Coords locationCoords = location.getCoords();
                                 LatLng donationCenter = new LatLng(locationCoords.getLatitude(), locationCoords.getLongitude());
                                 googleMap.addMarker(new MarkerOptions().position(donationCenter).title(location.getName()));
                             }

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


    }
}
