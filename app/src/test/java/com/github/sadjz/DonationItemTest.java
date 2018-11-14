package com.github.sadjz;


import com.github.sadjz.consts.RestEndpoints;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.managers.DonationItemManager;
import com.github.sadjz.managers.RestManager;
import com.github.sadjz.models.donationItem.DonationItemModel;
import com.github.sadjz.models.donationItem.ItemCategory;
import com.github.sadjz.models.login.LoginModel;
import com.github.sadjz.models.login.TokenModel;
import com.google.gson.internal.LinkedTreeMap;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import okhttp3.Callback;


/**
 * Example local unit test, which will execute on the development machine
 * (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DonationItemTest {
    /**
     * Example Unit Test
     */

    private final DonationItemManager donationItemManager = new DonationItemManager();
    private final RestManager loginRestManager = new RestManager();

    /**
     * Checks for Successful donation item
     */
    @SuppressWarnings("FeatureEnvy")
    @Test
    public void addDonationItemSuccess() {

        final CountDownLatch signal = new CountDownLatch(1);


        String name = "pants";
        String description = "cool";
        int quantity = 10;
        ItemCategory itemCategory  = ItemCategory.Accessory;
        String time = "2018-10-24T03:06:42.219Z";
        String id = "id";
        String locationid = "e3b712d839ce245e0f897ee00dfeb43ff603b336f23bba22c5ca46f9069a8a4a";
        final DonationItemModel donationItemModel =
                new DonationItemModel(name,description,quantity,itemCategory,time,id,locationid);
        assertEquals(donationItemModel.getName(), name);
        assertEquals(donationItemModel.getDescription(), description);
        assertEquals(donationItemModel.getQuantity(), quantity);
        assertEquals(donationItemModel.getCategory(), itemCategory);
        assertEquals(donationItemModel.getTime(), time);
        assertEquals(donationItemModel.getID(), id);
        assertEquals(donationItemModel.getLocationId(), locationid);


        assertEquals(donationItemModel.getName(), name);
        assertEquals(donationItemModel.getDescription(), description);
        assertEquals(donationItemModel.getQuantity(), quantity);
        assertEquals(donationItemModel.getCategory(), itemCategory);
        assertEquals(donationItemModel.getTime(), time);
        assertEquals(donationItemModel.getID(), id);
        assertEquals(donationItemModel.getLocationId(), locationid);


        LoginModel loginModel = new LoginModel("afshawn1@g.com", "123456");




        try {
            Callback tokenCallback =
                    new RestCallback<TokenModel>() {
                        @Override
                        public void invokeSuccess(final TokenModel tokenModel) {
                            assertNotNull(tokenModel);

                            try {
                                RestCallback<LinkedTreeMap> donationItemCallback =
                                        new RestCallback<LinkedTreeMap>() {
                                            @Override
                                            public void invokeSuccess(LinkedTreeMap model) {
                                                signal.countDown();
                                                assertNotNull(model);
                                                assertTrue(model.isEmpty());
                                            }

                                            @Override
                                            public void invokeFailure() {
                                                signal.countDown();
                                                fail("Failed to get donation item");
                                            }
                                        };

                                donationItemManager.addDonationItem(tokenModel,
                                        donationItemModel, donationItemCallback);
                            }catch (Exception e){
                                signal.countDown();
                                fail("Thread async exception");
                            }
                        }

                        @Override
                        public void invokeFailure() {
                            signal.countDown();
                            fail("Failed to get token");
                        }
                    };

            loginRestManager.postRequest(
                    RestEndpoints.Token, loginModel, tokenCallback, "");
            signal.await();
        }catch (Exception e){
            signal.countDown();
            fail("Thread async exception");
        }

    }





    /**
     * Checks for Failure donation item
     */
    @SuppressWarnings("FeatureEnvy")
    @Test
    public void addInvalidDonationItemSuccess() {

        final CountDownLatch signal = new CountDownLatch(1);


        //noinspection unused
        String name = null;
        //noinspection unused
        String description = null;
        int quantity = 10;
        ItemCategory itemCategory  = ItemCategory.Accessory;
        String time = "2018-10-24T03:06:42.219Z";
        String id = "id";
        String locationid = "e3b712d839ce245e0f897ee00dfeb43ff603b336f23bba22c5ca46f9069a8a4a";
        final DonationItemModel donationItemModel =
                new DonationItemModel(null, null,quantity,itemCategory,time,id,locationid);
        assertNull(donationItemModel.getName());
        assertNull(donationItemModel.getDescription());
        assertEquals(donationItemModel.getQuantity(), quantity);
        assertEquals(donationItemModel.getCategory(), itemCategory);
        assertEquals(donationItemModel.getTime(), time);
        assertEquals(donationItemModel.getID(), id);
        assertEquals(donationItemModel.getLocationId(), locationid);


        assertNull(donationItemModel.getName());
        assertNull(donationItemModel.getDescription());
        assertEquals(donationItemModel.getQuantity(), quantity);
        assertEquals(donationItemModel.getCategory(), itemCategory);
        assertEquals(donationItemModel.getTime(), time);
        assertEquals(donationItemModel.getID(), id);
        assertEquals(donationItemModel.getLocationId(), locationid);


        LoginModel loginModel = new LoginModel("afshawn1@g.com", "123456");




        try {
            Callback tokenCallback =
                    new RestCallback<TokenModel>() {
                        @Override
                        public void invokeSuccess(final TokenModel tokenModel) {
                            assertNotNull(tokenModel);

                            try {
                                RestCallback<LinkedTreeMap> donationItemCallback =
                                        new RestCallback<LinkedTreeMap>() {
                                            @Override
                                            public void invokeSuccess(LinkedTreeMap model) {
                                                signal.countDown();
                                                assertNotNull(model);
                                                assertFalse(model.isEmpty());
                                            }

                                            @Override
                                            public void invokeFailure() {
                                                signal.countDown();
                                                fail("Failed to get donation item");
                                            }
                                        };

                                donationItemManager
                                        .addDonationItem(tokenModel,
                                                donationItemModel,
                                                donationItemCallback);
                            }catch (Exception e){
                                signal.countDown();
                                fail("Thread async exception");
                            }
                        }

                        @Override
                        public void invokeFailure() {
                            signal.countDown();
                            fail("Failed to get token");
                        }
                    };

            loginRestManager.postRequest(
                    RestEndpoints.Token, loginModel, tokenCallback, "");
            signal.await();
        }catch (Exception e){
            signal.countDown();
            fail("Thread async exception");
        }

    }










}
