package com.github.sadjz.managers;

import com.github.sadjz.consts.RestEndpoints;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.models.donationItem.DonationItemModel;
import com.github.sadjz.models.login.TokenModel;
import com.google.gson.internal.LinkedTreeMap;

public class DonationItemManager {

    private final RestManager donationRestManager = new RestManager();

    public void addDonationItem(
            TokenModel token,
            DonationItemModel donationItem,
            RestCallback<LinkedTreeMap> addDonationItemCallback) {

        try {

            donationRestManager.postRequest(
                    token.token,
                    RestEndpoints.AddDonationItem,
                    donationItem,
                    addDonationItemCallback,
                    String.format("/atlanta/%s", donationItem.getLocationId()));

        } catch (Exception e) {
            addDonationItemCallback.invokeFailure();
        }
    }

    public void editDonationItem(
            TokenModel token,
            DonationItemModel donationItem,
            RestCallback<LinkedTreeMap> editDonationItemCallback) {

        try {

            donationRestManager.postRequest(
                    token.token,
                    RestEndpoints.EditDonationItem,
                    donationItem,
                    editDonationItemCallback,
                    String.format(
                            "/atlanta/%s/%s",
                            donationItem.getLocationId(),
                            donationItem.getID()));

        } catch (Exception e) {
            editDonationItemCallback.invokeFailure();
        }
    }

    public void removeDonationItem(
            TokenModel token,
            DonationItemModel donationItem,
            RestCallback<LinkedTreeMap> editDonationItemCallback) {

        try {

            donationRestManager.getRequest(
                    token.token,
                    RestEndpoints.RemoveDonationItem,
                    editDonationItemCallback,
                    String.format(
                            "/atlanta/%s/%s",
                            donationItem.getLocationId(),
                            donationItem.getID()));

        } catch (Exception e) {
            editDonationItemCallback.invokeFailure();
        }
    }
}
