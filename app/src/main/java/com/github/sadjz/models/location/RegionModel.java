package com.github.sadjz.models.location;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegionModel {


    private List<LocationModel> locations;

    @SerializedName("Locations")
    public List<LocationModel> getLocations() { return locations; }
    @SerializedName("Locations")
    public void setLocations(List<LocationModel> value) { this.locations = value; }


}

