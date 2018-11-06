package com.github.sadjz.models.location;


import android.os.Parcel;
import android.os.Parcelable;

import com.github.sadjz.models.donationItem.DonationItemModel;
import com.google.gson.annotations.SerializedName;


public class Coords implements Parcelable {
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;

    @Override
    public int describeContents() {
        return 0;
    }

    public Coords(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();


    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);

    }

    public static final Parcelable.Creator<Coords> CREATOR = new Parcelable.Creator<Coords>() {
        public Coords createFromParcel(Parcel in) {
            return new Coords(in);
        }

        public Coords[] newArray(int size) {
            return new Coords[size];
        }
    };


    public double getLatitude() { return latitude; }
    public void setLatitude(double value) { this.latitude = value; }


    public double getLongitude() { return longitude; }
    public void setLongitude(double value) { this.longitude = value; }

}

