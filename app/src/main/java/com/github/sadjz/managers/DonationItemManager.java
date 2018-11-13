package com.github.sadjz.managers;

import com.github.sadjz.consts.RestEndpoints;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.models.donationItem.DonationItemModel;
import com.github.sadjz.models.login.TokenModel;
import com.google.gson.internal.LinkedTreeMap;

/**
 * The type Donation item manager.
 */
public class DonationItemManager {

    private final RestManager donationRestManager = new RestManager();

    /**
     * Add donation item.
     *
     * @param token                   the token
     * @param donationItem            the donation item
     * @param addDonationItemCallback the add donation item callback
     */
    public void addDonationItem(
            TokenModel token,
            DonationItemModel donationItem,
            RestCallback<LinkedTreeMap> addDonationItemCallback) {

        try {

            donationRestManager.postRequest(
                    token.getToken(),
                    RestEndpoints.AddDonationItem,
                    donationItem,
                    addDonationItemCallback,
                    String.format("/atlanta/%s", donationItem.getLocationId()));

        } catch (Exception e) {
            addDonationItemCallback.invokeFailure();
        }
    }

    /**
     * Edit donation item.
     *
     * @param token                    the token
     * @param donationItem             the donation item
     * @param editDonationItemCallback the edit donation item callback
     */
    public void editDonationItem(
            TokenModel token,
            DonationItemModel donationItem,
            RestCallback<LinkedTreeMap> editDonationItemCallback) {

        try {

            donationRestManager.postRequest(
                    token.getToken(),
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

    /**
     * Remove donation item.
     *
     * @param token                    the token
     * @param donationItem             the donation item
     * @param editDonationItemCallback the edit donation item callback
     */
    public void removeDonationItem(
            TokenModel token,
            DonationItemModel donationItem,
            RestCallback<LinkedTreeMap> editDonationItemCallback) {

        try {

            donationRestManager.getRequest(
                    token.getToken(),
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
