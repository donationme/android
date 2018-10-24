package com.github.sadjz.models.location;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationModel {


    private List<LocationCollectionObject> locations;

    @SerializedName("Locations")
    public List<LocationCollectionObject> getLocations() { return locations; }
    @SerializedName("Locations")
    public void setLocations(List<LocationCollectionObject> value) { this.locations = value; }


}

