package com.github.sadjz.models.search;

import com.github.sadjz.models.donationItem.DonationItemModel;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

/**
 * The type Search model.
 */
public class SearchModel {
    @SuppressWarnings("unused")
    private List<DonationItemModel> results;

    /**
     * Gets results.
     *
     * @return the results
     */
    @SerializedName("Results")
    public List<DonationItemModel> getResults() {
        return Collections.unmodifiableList(results);
    }

}
