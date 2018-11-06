package com.github.sadjz.models.location;


import android.os.Parcel;
import android.os.Parcelable;

import com.github.sadjz.models.donationItem.DonationItemModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegionModel implements Parcelable {

    @SerializedName("locations")
    private List<LocationModel> locations;
    @SerializedName("regionCoords")
    private Coords coords;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;


    @Override
    public int describeContents() {
        return 0;
    }

    public RegionModel(Parcel in) {
        name = in.readString();
        coords = in.readParcelable(Coords.class.getClassLoader());

        id = in.readString();

    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(coords,0);
        dest.writeString(id);

    }

    public static final Parcelable.Creator<RegionModel> CREATOR = new Parcelable.Creator<RegionModel>() {
        public RegionModel createFromParcel(Parcel in) {
            return new RegionModel(in);
        }

        public RegionModel[] newArray(int size) {
            return new RegionModel[size];
        }
    };

    public List<LocationModel> getLocations() { return locations; }
    public void setLocations(List<LocationModel> value) { this.locations = value; }

    public Coords getCoords() { return coords; }
    public void setCoords(Coords value) { this.coords = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getId() { return id; }
    public void setId(String value) { this.id = value; }


}

