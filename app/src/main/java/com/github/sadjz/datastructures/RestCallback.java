package com.github.sadjz.datastructures;

import com.google.gson.Gson;

import java.io.IOException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

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

        try{
            T model = gson.fromJson(res, (Class<T>)
                    ((ParameterizedType)getClass().getGenericSuperclass())
                            .getActualTypeArguments()[0]);
            invokeSuccess(model);

        }catch(Exception e){
            //In case there is a JSON array
            T model = gson.fromJson(res,new TypeToken<T>(){}.getType());
            invokeSuccess(model);
        }

    }


    public abstract void invokeFailure();


    public abstract void invokeSuccess(T model);


}
