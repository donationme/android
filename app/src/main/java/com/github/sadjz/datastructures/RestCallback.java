package com.github.sadjz.datastructures;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class RestCallback<T> implements Callback {


    private final Gson gson = new Gson();


    @Override
    public void onFailure(Call call, IOException e) {
        call.cancel();
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String res = response.body().string();
        T model = gson.fromJson(res,(Class<T>)
                ((ParameterizedType)getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0]);
        invoke(model);

    }

    public abstract void invoke(T model);


}
