package com.github.sadjz.models.location;

import com.github.sadjz.models.donationItem.DonationItemModel;
import com.google.gson.annotations.SerializedName;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class LocationModel implements Parcelable{


    private long key;
    private String name;
    private double latitude;
    private double longitude;
    private String street;
    private String city;
    private String state;
    private long zip;
    private String address;
    private String type;
    private String phone;
    private String website;
    private String id;
    private List<DonationItemModel> items;

    @Override
    public int describeContents() {
        return 0;
    }

    public LocationModel(Parcel in) {
        key = in.readLong();
        name = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        street = in.readString();
        city = in.readString();
        state = in.readString();
        zip = in.readLong();
        address = in.readString();
        type = in.readString();
        phone = in.readString();
        website = in.readString();
        items = in.readArrayList(DonationItemModel.class.getClassLoader());
        id = in.readString();

    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(key);
        dest.writeString(name);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeLong(zip);
        dest.writeString(address);
        dest.writeString(type);
        dest.writeString(phone);
        dest.writeString(website);
        dest.writeList(items);
        dest.writeString(id);

    }

    public static final Parcelable.Creator<LocationModel> CREATOR = new Parcelable.Creator<LocationModel>() {
        public LocationModel createFromParcel(Parcel in) {
            return new LocationModel(in);
        }

        public LocationModel[] newArray(int size) {
            return new LocationModel[size];
        }
    };

    @SerializedName("Key")
    public long getKey() { return key; }
    @SerializedName("Key")
    public void setKey(long value) { this.key = value; }

    @SerializedName("Name")
    public String getName() { return name; }
    @SerializedName("Name")
    public void setName(String value) { this.name = value; }

    @SerializedName("Latitude")
    public double getLatitude() { return latitude; }
    @SerializedName("Latitude")
    public void setLatitude(double value) { this.latitude = value; }

    @SerializedName("Longitude")
    public double getLongitude() { return longitude; }
    @SerializedName("Longitude")
    public void setLongitude(double value) { this.longitude = value; }

    @SerializedName("Street")
    public String getStreet() { return street; }
    @SerializedName("Street")
    public void setStreet(String value) { this.street = value; }

    @SerializedName("City")
    public String getCity() { return city; }
    @SerializedName("City")
    public void setCity(String value) { this.city = value; }

    @SerializedName("State")
    public String getState() { return state; }
    @SerializedName("State")
    public void setState(String value) { this.state = value; }

    @SerializedName("Zip")
    public long getZip() { return zip; }
    @SerializedName("Zip")
    public void setZip(long value) { this.zip = value; }

    @SerializedName("Address")
    public String getAddress() { return address; }
    @SerializedName("Address")
    public void setAddress(String value) { this.address = value; }

    @SerializedName("Type")
    public String getType() { return type; }
    @SerializedName("Type")
    public void setType(String value) { this.type = value; }

    @SerializedName("Phone")
    public String getPhone() { return phone; }
    @SerializedName("Phone")
    public void setPhone(String value) { this.phone = value; }

    @SerializedName("Website")
    public String getWebsite() { return website; }
    @SerializedName("Website")
    public void setWebsite(String value) { this.website = value; }


    @SerializedName("Id")
    public String getId() { return id; }
    @SerializedName("Id")
    public void getId(String value) { this.id = value; }

    @SerializedName("Items")
    public List<DonationItemModel> getItems() { return items ; }
    @SerializedName("Items")
    public void setItems( List<DonationItemModel> value) { this.items = value; }
}
