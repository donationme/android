package com.github.sadjz.models.donationItem;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class DonationItemModel implements Parcelable {
    @SerializedName("name")
    private final String name;

    @SerializedName("description")
    private final String description;

    @SerializedName("quantity")
    private final int quantity;

    @SerializedName("category")
    private final ItemCategory category;

    @SerializedName("id")
    private final String id;

    @SerializedName("time")
    private final String time;

    @SerializedName("locationId")
    private final String locationId;

    public DonationItemModel(
            String name,
            String description,
            int quantity,
            ItemCategory category,
            String time,
            String id,
            String locationid) {
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(name);
        dest.writeString(id);
        dest.writeInt(quantity);
        dest.writeString(category.toString());
        dest.writeString(time);
        dest.writeString(locationId);
    }

    public static final Parcelable.Creator<DonationItemModel> CREATOR =
            new Parcelable.Creator<DonationItemModel>() {
                public DonationItemModel createFromParcel(Parcel in) {
                    return new DonationItemModel(in);
                }

                public DonationItemModel[] newArray(int size) {
                    return new DonationItemModel[size];
                }
            };

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public String getTime() {
        return time;
    }

    public String getID() {
        return id;
    }

    public String getLocationId() {
        return locationId;
    }

}
