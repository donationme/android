package com.github.sadjz.models.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.github.sadjz.models.donationItem.DonationItemModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Location model.
 */
public final class LocationModel implements Parcelable {

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
        this.donationItems =  in.readArrayList(DonationItemModel.class.getClassLoader());
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

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<LocationModel> CREATOR =
            new Parcelable.Creator<LocationModel>() {
                @Override
                public LocationModel createFromParcel(Parcel in) {
                    return new LocationModel(in);
                }

                @Override
                public LocationModel[] newArray(int size) {
                    return new LocationModel[size];
                }
            };



    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets coords.
     *
     * @return the coords
     */
    @SuppressWarnings("unused")
    public Coords getCoords() {
        return coords;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets website.
     *
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets donation items.
     *
     * @return the donation items
     */
    public List<DonationItemModel> getDonationItems() {
        return Collections.unmodifiableList(donationItems);
    }

    /**
     * Sets donation items.
     *
     * @param value the value
     */
    public void setDonationItems(List<DonationItemModel> value) {
        donationItems = new ArrayList<>(value);
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
