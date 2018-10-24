package com.github.sadjz.models.donationItem;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DonationItemModel implements Parcelable{

    private String name;
    private String description;
    private int quantity;
    private ItemCategory category;
    private String id;
    private String time;
    private String locationid;

    public DonationItemModel(String name, String description, int quantity, ItemCategory category, String time, String id, String locationid){
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.category = category;
        this.time = time;
        this.id = id;
        this.locationid = locationid;
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
        locationid = in.readString();


    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(name);
        dest.writeString(id);
        dest.writeInt(quantity);
        dest.writeString(category.toString());
        dest.writeString(time);
        dest.writeString(locationid);


    }

    public static final Parcelable.Creator<DonationItemModel> CREATOR = new Parcelable.Creator<DonationItemModel>() {
        public DonationItemModel createFromParcel(Parcel in) {
            return new DonationItemModel(in);
        }

        public DonationItemModel[] newArray(int size) {
            return new DonationItemModel[size];
        }
    };


    @SerializedName("Description")
    public String getDescription() { return description; }
    @SerializedName("Description")
    public void setDescription(String value) { this.description = value; }

    @SerializedName("Name")
    public String getName() { return name; }
    @SerializedName("Name")
    public void setName(String value) { this.name = value; }

    @SerializedName("Quantity")
    public int getQuantity() { return quantity; }
    @SerializedName("Quantity")
    public void setQuantity(int value) { this.quantity = value; }

    @SerializedName("Category")
    public ItemCategory getCategory() { return category; }
    @SerializedName("Category")
    public void setCategory(ItemCategory value) { this.category = value; }


    @SerializedName("Time")
    public String getTime() { return time; }
    @SerializedName("Time")
    public void setTime(String value) { this.time = value; }


    @SerializedName("Id")
    public String getID() { return id; }
    @SerializedName("Id")
    public void setID(String value) { this.id = value; }

    @SerializedName("Locationid")
    public String getLocationid() { return locationid; }
    @SerializedName("Locationid")
    public void setLocationid(String value) { this.locationid = value; }

}
