package com.github.sadjz.managers;

import com.github.sadjz.consts.AppConst;
import com.github.sadjz.consts.RestEndpoints;
import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * The type Rest manager.
 */
public class RestManager {
    private final Call.Factory client = new OkHttpClient();
    private static final String serverAddress =
            String.format("http://%s:5000/", AppConst.serverAddress);
    private final Gson gson = new Gson();
    private Call currentCall;
    private static final MediaType JSON =
            MediaType.parse("application/json; charset=utf-8");

    /**
     * Aborts request to server
     */
    public void abortRequest() {
        if (this.currentCall != null) {
            this.currentCall.cancel();
        }
    }

    /**
     * Gets request.
     *
     * @param token    JWT Token
     * @param endpoint Endpoint Url Enum
     * @param callback Callback to execute afterwards
     * @param args     the args
     */
    public void getRequest(
            String token,
            RestEndpoints endpoint,
            Callback callback,
            String args) {

        Request.Builder requestBuilder =
                new Request.Builder();
        requestBuilder.url(
                                RestManager.serverAddress
                                        + endpoint.getEndpointPath()
                                        + args);
        requestBuilder.get();

        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer " + token);
        }

        Request request = requestBuilder.build();

        this.setCurrentCall(request, callback);
    }

    /**
     * Post request.
     *
     * @param <T>      the type parameter
     * @param endpoint Endpoint Url Enum
     * @param model    the model
     * @param callback Callback to execute afterwards
     * @param args     the args
     */
    public <T> void postRequest(
            RestEndpoints endpoint, T model, Callback callback, String args) {
        this.postRequest(null, endpoint, model, callback, args);
    }

    /**
     * Post request.
     *
     * @param <T>      the type parameter
     * @param token    JWT Token
     * @param endpoint Endpoint Url Enum
     * @param model    the model
     * @param callback Callback to execute afterwards
     * @param args     the args
     */
    public <T> void postRequest(
            String token,
            RestEndpoints endpoint,
            T model,
            Callback callback,
            String args) {

        String json = gson.toJson(model);
        RequestBody body = RequestBody.create(JSON, json);

        Request.Builder requestBuilder =
                new Request.Builder();
        requestBuilder.url(
                                RestManager.serverAddress
                                        + endpoint.getEndpointPath()
                                        + args);
        requestBuilder.post(body);

        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer " + token);
        }

        Request request = requestBuilder.build();

        this.setCurrentCall(request, callback);
    }

    private void setCurrentCall(Request request, Callback callback) {
        this.currentCall = client.newCall(request);
        this.currentCall.enqueue(callback);
    }

}
