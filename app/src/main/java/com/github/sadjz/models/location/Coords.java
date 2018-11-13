package com.github.sadjz.models.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * The type Coords.
 */
public class Coords implements Parcelable {
    @SerializedName("latitude")
    private final double latitude;

    @SerializedName("longitude")
    private final double longitude;

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Instantiates a new Coords.
     *
     * @param in the in
     */
    public Coords(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<Coords> CREATOR =
            new Parcelable.Creator<Coords>() {
                @Override
                public Coords createFromParcel(Parcel in) {
                    return new Coords(in);
                }

                @Override
                public Coords[] newArray(int size) {
                    return new Coords[size];
                }
            };

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

}
