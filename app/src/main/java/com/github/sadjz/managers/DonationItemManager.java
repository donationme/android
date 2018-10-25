package com.github.sadjz.managers;


import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.models.account.ServerResponse;
import com.github.sadjz.models.donationItem.DonationItemModel;
import com.github.sadjz.models.login.RestEndpoints;
import com.github.sadjz.models.login.TokenModel;


public class DonationItemManager {


    private final RestManager donationRestManager = new RestManager();




    public void addDonationItem(TokenModel token, DonationItemModel donationItem, RestCallback<ServerResponse[]> addDonationItemCallback) {

        try{

            donationRestManager.postRequest(token.token, RestEndpoints.AddDonationItem, donationItem, addDonationItemCallback, String.format("/%s",donationItem.getLocationid()));

        }catch (Exception e){
            addDonationItemCallback.invokeFailure();
        }

    }




    public void editDonationItem(TokenModel token, DonationItemModel donationItem, RestCallback<ServerResponse[]> editDonationItemCallback) {

        try{

            donationRestManager.postRequest(token.token, RestEndpoints.EditDonationItem, donationItem, editDonationItemCallback, String.format("/%s/%s",donationItem.getLocationid(), donationItem.getID()));

        }catch (Exception e){
            editDonationItemCallback.invokeFailure();
        }

    }
    public void removeDonationItem(TokenModel token, DonationItemModel donationItem, RestCallback<ServerResponse[]> editDonationItemCallback) {

        try{

            donationRestManager.getRequest(token.token, RestEndpoints.RemoveDonationItem, editDonationItemCallback, String.format("/%s/%s",donationItem.getLocationid(), donationItem.getID()));

        }catch (Exception e){
            editDonationItemCallback.invokeFailure();
        }

    }




}
