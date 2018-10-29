package com.github.sadjz.models.search;
import com.github.sadjz.models.donationItem.DonationItemModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchModel {
    private List<DonationItemModel> results;

    @SerializedName("Results")
    public List<DonationItemModel> getResults() { return results; }
    @SerializedName("Results")
    public void setResults(List<DonationItemModel> value) { this.results = value; }

}