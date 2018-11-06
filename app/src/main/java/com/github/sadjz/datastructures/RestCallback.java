package com.github.sadjz.datastructures;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class RestCallback<T> implements Callback {


    private final Gson gson = new Gson();


    @Override
    public void onFailure(Call call, IOException e) {
        call.cancel();
        this.invokeFailure();
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String res = response.body().string();
        T model;

        try{

            model = gson.fromJson(res, (Class<T>)
                    ((ParameterizedType)getClass().getGenericSuperclass())
                            .getActualTypeArguments()[0]);
            invokeSuccess(model);

        }catch(Exception e){
            Type type = new TypeToken<T>(){}.getType();

            model = gson.fromJson(res, type);
            //In case there is a JSON array
            invokeSuccess(model);
        }


    }


    public abstract void invokeFailure();


    public abstract void invokeSuccess(T model);


}
