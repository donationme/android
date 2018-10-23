package com.github.sadjz.managers;


import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.models.donationItem.DonationItemModel;
import com.github.sadjz.models.login.RestEndpoints;
import com.github.sadjz.models.login.TokenModel;


public class ItemManager {


    private final RestManager itemRestManager = new RestManager();



    public void abortLocationFetch(){
        this.itemRestManager.abortRequest();
    }


    public void getItems(TokenModel token, RestCallback<DonationItemModel> donationItemCallback) {

        try{

            itemRestManager.getRequest(token.token, RestEndpoints.Item, donationItemCallback);

        }catch (Exception e){
            donationItemCallback.invokeFailure();
        }

    }




}
