package com.github.sadjz.managers;

import com.github.sadjz.consts.RestEndpoints;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.models.location.RegionModel;
import com.github.sadjz.models.login.TokenModel;

/**
 * The type Location manager.
 */
public class LocationManager {

    private final RestManager locationRestManager = new RestManager();

    /**
     * Gets locations.
     *
     * @param token            the token
     * @param locationCallback the location callback
     */
    public void getLocations(
            TokenModel token, RestCallback<RegionModel> locationCallback) {

        try {

            locationRestManager.getRequest(
                    token.getToken(),
                    RestEndpoints.Region,
                    locationCallback,
                    "/atlanta");

        } catch (Exception e) {
            locationCallback.invokeFailure();
        }
    }
}
