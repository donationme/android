package com.github.sadjz.models.location;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationModel {


    private List<LocationListObject> locations;

    @SerializedName("Locations")
    public List<LocationListObject> getLocations() { return locations; }
    @SerializedName("Locations")
    public void setLocations(List<LocationListObject> value) { this.locations = value; }


}

