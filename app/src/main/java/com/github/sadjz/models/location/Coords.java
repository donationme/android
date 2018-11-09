package com.github.sadjz.models.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Coords implements Parcelable {
    @SerializedName("latitude")
    private final double latitude;

    @SerializedName("longitude")
    private final double longitude;

    @Override
    public int describeContents() {
        return 0;
    }

    public Coords(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    public static final Parcelable.Creator<Coords> CREATOR =
            new Parcelable.Creator<Coords>() {
                public Coords createFromParcel(Parcel in) {
                    return new Coords(in);
                }

                public Coords[] newArray(int size) {
                    return new Coords[size];
                }
            };

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
