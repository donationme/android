package com.github.sadjz.managers;


import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.models.location.RegionModel;
import com.github.sadjz.models.login.RestEndpoints;
import com.github.sadjz.models.login.TokenModel;


public class LocationManager {


    private final RestManager locationRestManager = new RestManager();



    public void abortLocationFetch(){
        this.locationRestManager.abortRequest();
    }


    public void getLocations(TokenModel token, RestCallback<RegionModel> locationCallback) {

        try{

            locationRestManager.getRequest(token.token, RestEndpoints.Location, locationCallback, "");

        }catch (Exception e){
            locationCallback.invokeFailure();
        }

    }




}
