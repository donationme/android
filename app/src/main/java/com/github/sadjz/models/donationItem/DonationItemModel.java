package com.github.sadjz.models.donationItem;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * The type Donation item model.
 */

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

    /**
     * Instantiates a new Donation item model.
     *
     * @param name        the name
     * @param description the description
     * @param quantity    the quantity
     * @param category    the category
     * @param time        the time
     * @param id          the id
     * @param locationid  the locationid
     */
    @SuppressWarnings("ConstructorWithTooManyParameters")
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

    /**
     * Instantiates a new Donation item model.
     *
     * @param in the in
     */
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

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<DonationItemModel> CREATOR =
            new Parcelable.Creator<DonationItemModel>() {
                @Override
                public DonationItemModel createFromParcel(Parcel in) {
                    return new DonationItemModel(in);
                }

                @Override
                public DonationItemModel[] newArray(int size) {
                    return new DonationItemModel[size];
                }
            };

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public ItemCategory getCategory() {
        return category;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getID() {
        return id;
    }

    /**
     * Gets location id.
     *
     * @return the location id
     */
    public String getLocationId() {
        return locationId;
    }

}
