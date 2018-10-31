package com.github.sadjz.managers;


import com.github.sadjz.consts.AppConst;
import com.github.sadjz.consts.RestEndpoints;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class RestManager  {
    private final OkHttpClient client = new OkHttpClient();
    static final String serverAddress = String.format("http://%s:5000/", AppConst.serverAddress);
    private final Gson gson = new Gson();
    private Call currentCall;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    /**
     * Aborts request to server
     */
    public void abortRequest(){
        if (this.currentCall != null){
            this.currentCall.cancel();
        }
    }

    /**
     *
     * @param endpoint Endpoint Url Enum
     * @param callback Callback to execute afterwards
     * @throws IOException
     */
    public void getRequest(RestEndpoints endpoint, Callback callback, String args) throws IOException {

        this.getRequest(null, endpoint, callback, args);
    }


    /**
     *
     * @param token JWT Token
     * @param endpoint Endpoint Url Enum
     * @param callback Callback to execute afterwards
     * @throws IOException
     */
    public void getRequest(String token, RestEndpoints endpoint, Callback callback, String args) throws IOException {


        Request.Builder requestBuilder = new Request.Builder()
                .url(RestManager.serverAddress + endpoint.getEndpointPath() + args)
                .get();

        if (token != null){
            requestBuilder.addHeader("Authorization", "Bearer " + token);
        }


        Request request = requestBuilder.build();

        this.setCurrentCall(request, callback);

    }

    /**
     *
     * @param endpoint Endpoint Url Enum
     * @param callback Callback to execute afterwards
     * @throws IOException
     */
    public <T> void postRequest(RestEndpoints endpoint, T model, Callback callback, String args) throws IOException {
        this.postRequest(null, endpoint, model, callback, args);

    }


    /**
     *
     * @param token JWT Token
     * @param endpoint Endpoint Url Enum
     * @param callback Callback to execute afterwards
     * @throws IOException
     */
    public <T> void postRequest(String token, RestEndpoints endpoint, T model, Callback callback, String args) throws IOException {

        OkHttpClient client = new OkHttpClient();
        String json = gson.toJson(model);
        RequestBody body = RequestBody.create(JSON, json);

        Request.Builder requestBuilder = new Request.Builder()
                .url(RestManager.serverAddress + endpoint.getEndpointPath() + args)
                .post(body);


        if (token != null){
            requestBuilder.addHeader("Authorization", "Bearer " + token);
        }


        Request request = requestBuilder.build();

        this.setCurrentCall(request, callback);


    }

    private void setCurrentCall(Request request, Callback callback){
        this.currentCall = client.newCall(request);
        this.currentCall.enqueue(callback);

    }

    private Call getCurrentCall(){
       return this.currentCall;

    }




}
