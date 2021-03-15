package com.github.sadjz.datastructures;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Callback for Rest Manager
 * @param <T> Type of rest callback returns
 */

@SuppressWarnings("ClassWithTooManyDependents")
public abstract class RestCallback<T> implements Callback {

    private final Gson gson = new Gson();

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        call.cancel();
        this.invokeFailure();
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response)  {

        ResponseBody body = response.body();
        try{
            if (body != null) {


                String res = body.string();

                T model;

                Class classT = (getClass());
                    ParameterizedType pType = ((ParameterizedType) classT.getGenericSuperclass());
                    if (pType != null) {

                        model =
                                gson.fromJson(
                                        res,
                                        pType.getActualTypeArguments()[0]);
                        invokeSuccess(model);
                    }else{
                        @SuppressWarnings("EmptyClass") TypeToken typeToken = new TypeToken<T>() {};
                        Type type = typeToken.getType();

                        model = gson.fromJson(res, type);
                        // In case there is a JSON array
                        invokeSuccess(model);
                    }


            }else {
                this.invokeFailure();
            }
        }catch(IOException i){
            invokeFailure();
        }
    }

    /**
     * Calls on failure
     */
    public abstract void invokeFailure();

    /**
     * Calls on success
     * @param model Model returned on 200
     */
    protected abstract void invokeSuccess(T model);
}
