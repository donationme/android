package com.github.sadjz.models.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.github.sadjz.models.donationItem.DonationItemModel;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class LocationModel implements Parcelable {

    @SerializedName("key")
    private final long key;

    @SerializedName("name")
    private final String name;

    @SerializedName("coords")
    private final Coords coords;

    @SerializedName("street")
    private final String street;

    @SerializedName("city")
    private final String city;

    @SerializedName("state")
    private final String state;

    @SerializedName("zip")
    private final long zip;

    @SerializedName("address")
    private final String address;

    @SerializedName("type")
    private final String type;

    @SerializedName("phone")
    private final String phone;

    @SerializedName("website")
    private final String website;

    @SerializedName("id")
    private final String id;

    @SerializedName("donationItems")
    private List<DonationItemModel> donationItems;

    @Override
    public int describeContents() {
        return 0;
    }

    private LocationModel(Parcel in) {
        key = in.readLong();
        name = in.readString();
        coords = in.readParcelable(Coords.class.getClassLoader());
        street = in.readString();
        city = in.readString();
        state = in.readString();
        zip = in.readLong();
        address = in.readString();
        type = in.readString();
        phone = in.readString();
        website = in.readString();
        donationItems =
                in.readArrayList(DonationItemModel.class.getClassLoader());
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(key);
        dest.writeString(name);
        dest.writeParcelable(coords, 0);
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeLong(zip);
        dest.writeString(address);
        dest.writeString(type);
        dest.writeString(phone);
        dest.writeString(website);
        dest.writeList(donationItems);
        dest.writeString(id);
    }

    public static final Parcelable.Creator<LocationModel> CREATOR =
            new Parcelable.Creator<LocationModel>() {
                public LocationModel createFromParcel(Parcel in) {
                    return new LocationModel(in);
                }

                public LocationModel[] newArray(int size) {
                    return new LocationModel[size];
                }
            };

    public String getName() {
        return name;
    }

    public Coords getCoords() {
        return coords;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getId() {
        return id;
    }

    public List<DonationItemModel> getDonationItems() {
        return Collections.unmodifiableList(donationItems);
    }

    public void setDonationItems(List<DonationItemModel> value) {
        this.donationItems = value;
    }
}
