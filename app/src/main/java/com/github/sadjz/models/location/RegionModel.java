package com.github.sadjz.models.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

/**
 * The type Region model.
 */
@SuppressWarnings("unused")
public final class RegionModel implements Parcelable {

    @SerializedName("locations")
    private List<LocationModel> locations;

    @SerializedName("regionCoords")
    private final Coords coords;

    @SerializedName("name")
    private final String name;

    @SerializedName("id")
    private final String id;

    @Override
    public int describeContents() {
        return 0;
    }

    private RegionModel(Parcel in) {
        name = in.readString();
        coords = in.readParcelable(Coords.class.getClassLoader());

        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(coords, 0);
        dest.writeString(id);
    }

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<RegionModel> CREATOR =
            new Parcelable.Creator<RegionModel>() {
                @Override
                public RegionModel createFromParcel(Parcel in) {
                    return new RegionModel(in);
                }

                @Override
                public RegionModel[] newArray(int size) {
                    return new RegionModel[size];
                }
            };

    /**
     * Gets locations.
     *
     * @return the locations
     */
    public List<LocationModel> getLocations() {
        return Collections.unmodifiableList(locations);
    }

    /**
     * Gets coords.
     *
     * @return the coords
     */
    public Coords getCoords() {
        return coords;
    }


    /**
     * Gets latitude
     * @return the latitude
     */
    public double getLatitude() {return this.coords.getLatitude();}


    /**
     * Gets longitude
     * @return the longitude
     */
    public double getLongitude() {return this.coords.getLongitude();}


}
