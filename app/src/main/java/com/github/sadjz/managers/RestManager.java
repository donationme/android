package com.github.sadjz.managers;


import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.github.sadjz.models.login.RestEndpoints;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Optional;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RestManager<T>  {

    static final String serverAddress = "http://10.0.1.17:5000/";
    private final Gson gson = new Gson();

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    /**
     *
     * @param endpoint Endpoint Url Enum
     * @param callback Callback to execute afterwards
     * @throws IOException
     */
    public void getRequest(RestEndpoints endpoint, Callback callback) throws IOException {

        this.getRequest(null, endpoint, callback);
    }


    /**
     *
     * @param token JWT Token
     * @param endpoint Endpoint Url Enum
     * @param callback Callback to execute afterwards
     * @throws IOException
     */
    public void getRequest(String token, RestEndpoints endpoint, Callback callback) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request.Builder requestBuilder = new Request.Builder()
                .url(RestManager.serverAddress + endpoint.getEndpointPath())
                .get();

        if (token != null){
            requestBuilder.addHeader("Authorization", "Bearer " + token);
        }


        Request request = requestBuilder.build();


        client.newCall(request).enqueue(callback);

    }

    /**
     *
     * @param endpoint Endpoint Url Enum
     * @param callback Callback to execute afterwards
     * @throws IOException
     */
    public void postRequest(RestEndpoints endpoint, T model, Callback callback) throws IOException {
        this.postRequest(null, endpoint, model, callback);

    }


    /**
     *
     * @param token JWT Token
     * @param endpoint Endpoint Url Enum
     * @param callback Callback to execute afterwards
     * @throws IOException
     */
    public void postRequest(String token, RestEndpoints endpoint, T model, Callback callback) throws IOException {

        OkHttpClient client = new OkHttpClient();
        String json = gson.toJson(model);
        RequestBody body = RequestBody.create(JSON, json);

        Request.Builder requestBuilder = new Request.Builder()
                .url(RestManager.serverAddress + endpoint.getEndpointPath())
                .post(body);


        if (token != null){
            requestBuilder.addHeader("Authorization", "Bearer " + token);
        }


        Request request = requestBuilder.build();

        client.newCall(request).enqueue(callback);

    }
}
