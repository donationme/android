package com.github.sadjz.models.location;

import com.github.sadjz.models.donationItem.DonationItemModel;
import com.google.gson.annotations.SerializedName;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class LocationModel implements Parcelable{









    @SerializedName("key")
    private long key;
    @SerializedName("name")
    private String name;
    @SerializedName("coords")
    private Coords coords;
    @SerializedName("street")
    private String street;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("zip")
    private long zip;
    @SerializedName("address")
    private String address;
    @SerializedName("type")
    private String type;
    @SerializedName("phone")
    private String phone;
    @SerializedName("website")
    private String website;
    @SerializedName("id")
    private String id;
    @SerializedName("donationItems")
    private List<DonationItemModel> donationitems;

    @Override
    public int describeContents() {
        return 0;
    }

    public LocationModel(Parcel in) {
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
        donationitems = in.readArrayList(DonationItemModel.class.getClassLoader());
        id = in.readString();

    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(key);
        dest.writeString(name);
        dest.writeParcelable(coords,0);
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeLong(zip);
        dest.writeString(address);
        dest.writeString(type);
        dest.writeString(phone);
        dest.writeString(website);
        dest.writeList(donationitems);
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

    public long getKey() { return key; }
    public void setKey(long value) { this.key = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public Coords getCoords() { return coords; }
    public void setCoords(Coords value) { this.coords = value; }


    public String getStreet() { return street; }
    public void setStreet(String value) { this.street = value; }

    public String getCity() { return city; }
    public void setCity(String value) { this.city = value; }

    public String getState() { return state; }
    public void setState(String value) { this.state = value; }

    public long getZip() { return zip; }
    public void setZip(long value) { this.zip = value; }

    public String getAddress() { return address; }
    public void setAddress(String value) { this.address = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public String getPhone() { return phone; }
    public void setPhone(String value) { this.phone = value; }

    public String getWebsite() { return website; }
    public void setWebsite(String value) { this.website = value; }


    public String getId() { return id; }
    public void getId(String value) { this.id = value; }

    public List<DonationItemModel> getDonationitems() { return donationitems; }
    public void setDonationitems(List<DonationItemModel> value) { this.donationitems = value; }
}
