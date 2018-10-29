package com.github.sadjz.managers;

import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.models.login.RestEndpoints;
import com.github.sadjz.models.login.TokenModel;
import com.github.sadjz.models.search.SearchModel;

public class SearchManager {


    private final RestManager searchRestManager = new RestManager();


    public void searchNameQuery(TokenModel token, String query ,  RestCallback<SearchModel> searchCallback) {

        try{

            searchRestManager.getRequest(token.token, RestEndpoints.SearchName, searchCallback, String.format("/%s",query));

        }catch (Exception e){
            searchCallback.invokeFailure();
        }

    }

    public void abortSearch(){
        this.searchRestManager.abortRequest();

    }

    public void searchCategory(TokenModel token, String query , RestCallback<SearchModel> searchCallback) {

        try{

            searchRestManager.getRequest(token.token, RestEndpoints.SearchCategory, searchCallback, String.format("/%s",query));

        }catch (Exception e){
            searchCallback.invokeFailure();
        }

    }


}
