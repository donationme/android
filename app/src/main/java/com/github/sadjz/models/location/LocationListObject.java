package com.github.sadjz.models.location;

import com.google.gson.annotations.SerializedName;
import android.os.Parcel;
import android.os.Parcelable;

public class LocationListObject implements Parcelable{


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
    private Object id;

    @Override
    public int describeContents() {
        return 0;
    }

    public LocationListObject(Parcel in) {
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
    }

    public static final Parcelable.Creator<LocationListObject> CREATOR = new Parcelable.Creator<LocationListObject>() {
        public LocationListObject createFromParcel(Parcel in) {
            return new LocationListObject(in);
        }

        public LocationListObject[] newArray(int size) {
            return new LocationListObject[size];
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

    @SerializedName("id")
    public Object getID() { return id; }
    @SerializedName("id")
    public void setID(Object value) { this.id = value; }
}
