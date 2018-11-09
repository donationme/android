package com.github.sadjz.models.search;

import com.github.sadjz.models.donationItem.DonationItemModel;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class SearchModel {
    private List<DonationItemModel> results;

    @SerializedName("Results")
    public List<DonationItemModel> getResults() {
        return Collections.unmodifiableList(results);
    }

}
