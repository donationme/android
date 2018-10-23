package com.github.sadjz.models.donationItem;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DonationItemModel {

    private List<DonationItemListObject> items;

    @SerializedName("Locations")
    public List<DonationItemListObject> getDonationItems() { return items; }
    @SerializedName("Locations")
    public void setDonationItems(List<DonationItemListObject> value) { this.items = value; }


}
