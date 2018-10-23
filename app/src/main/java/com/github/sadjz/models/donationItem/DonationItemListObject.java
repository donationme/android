package com.github.sadjz.models.donationItem;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DonationItemListObject implements Parcelable{

    private String timeStamp;
    private String location;
    private String shortDescription;
    private String longDescription;
    private long value;
    private String category;
    private String comments;

    @Override
    public int describeContents() {
        return 0;
    }

    public DonationItemListObject(Parcel in) {
        timeStamp = in.readString();
        location = in.readString();
        shortDescription = in.readString();
        longDescription = in.readString();
        value = in.readLong();
        category = in.readString();
        comments = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(timeStamp);
        dest.writeString(location);
        dest.writeString(shortDescription);
        dest.writeString(longDescription);
        dest.writeLong(value);
        dest.writeString(category);
        dest.writeString(comments);
    }

    public static final Parcelable.Creator<DonationItemListObject> CREATOR = new Parcelable.Creator<DonationItemListObject>() {
        public DonationItemListObject createFromParcel(Parcel in) {
            return new DonationItemListObject(in);
        }

        public DonationItemListObject[] newArray(int size) {
            return new DonationItemListObject[size];
        }
    };

    @SerializedName("TimeStamp")
    public String getTimeStamp() { return timeStamp; }
    @SerializedName("TimeStamp")
    public void setTimeStamp(String value) { this.timeStamp = value; }

    @SerializedName("Location")
    public String getLocation() { return location; }
    @SerializedName("Location")
    public void setLocation(String value) { this.location = value; }

    @SerializedName("ShortDescription")
    public String getShortDescription() { return shortDescription; }
    @SerializedName("ShortDescription")
    public void setShortDescription(String value) { this.shortDescription = value; }

    @SerializedName("LongDescription")
    public String getLongDescription() { return longDescription; }
    @SerializedName("LongDescription")
    public void setLongDescription(String value) { this.longDescription = value; }

    @SerializedName("Value")
    public long getValue() { return value; }
    @SerializedName("Value")
    public void setValue(long value) { this.value = value; }

    @SerializedName("Category")
    public String getCategory() { return category; }
    @SerializedName("Category")
    public void setCategory(String value) { this.category = value; }

    @SerializedName("Comments")
    public String getComments() { return comments; }
    @SerializedName("Comments")
    public void setComments(String value) { this.comments = value; }

}
