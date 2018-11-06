package com.github.sadjz.models.donationItem;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DonationItemModel implements Parcelable{
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("category")
    private ItemCategory category;
    @SerializedName("id")
    private String id;
    @SerializedName("time")
    private String time;
    @SerializedName("locationId")
    private String locationId;

    public DonationItemModel(String name, String description, int quantity, ItemCategory category, String time, String id, String locationid){
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.category = category;
        this.time = time;
        this.id = id;
        this.locationId = locationid;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    public DonationItemModel(Parcel in) {

        description = in.readString();
        name = in.readString();
        id = in.readString();
        quantity = in.readInt();
        category = ItemCategory.valueOf(in.readString());
        time = in.readString();
        locationId = in.readString();


    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(name);
        dest.writeString(id);
        dest.writeInt(quantity);
        dest.writeString(category.toString());
        dest.writeString(time);
        dest.writeString(locationId);


    }

    public static final Parcelable.Creator<DonationItemModel> CREATOR = new Parcelable.Creator<DonationItemModel>() {
        public DonationItemModel createFromParcel(Parcel in) {
            return new DonationItemModel(in);
        }

        public DonationItemModel[] newArray(int size) {
            return new DonationItemModel[size];
        }
    };


    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int value) { this.quantity = value; }

    public ItemCategory getCategory() { return category; }
    public void setCategory(ItemCategory value) { this.category = value; }


    public String getTime() { return time; }
    public void setTime(String value) { this.time = value; }


    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getLocationId() { return locationId; }
    public void setLocationId(String value) { this.locationId = value; }

}
