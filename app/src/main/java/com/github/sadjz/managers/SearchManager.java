package com.github.sadjz.managers;

import com.github.sadjz.consts.RestEndpoints;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.models.login.TokenModel;
import com.github.sadjz.models.search.SearchModel;

public class SearchManager {

    private final RestManager searchRestManager = new RestManager();

    public void abortSearch() {
        this.searchRestManager.abortRequest();
    }

    public void searchAllName(
            TokenModel token,
            String query,
            RestCallback<SearchModel> searchCallback) {

        try {

            searchRestManager.getRequest(
                    token.token,
                    RestEndpoints.SearchAllName,
                    searchCallback,
                    String.format("/atlanta/%s", query));

        } catch (Exception e) {
            searchCallback.invokeFailure();
        }
    }

    public void searchAllCategory(
            TokenModel token,
            String query,
            RestCallback<SearchModel> searchCallback) {

        try {

            searchRestManager.getRequest(
                    token.token,
                    RestEndpoints.SearchAllCategory,
                    searchCallback,
                    String.format("/atlanta/%s", query));

        } catch (Exception e) {
            searchCallback.invokeFailure();
        }
    }

    public void searchSpecificName(
            TokenModel token,
            String query,
            RestCallback<SearchModel> searchCallback,
            String locationId) {

        try {

            searchRestManager.getRequest(
                    token.token,
                    RestEndpoints.SearchSpecificName,
                    searchCallback,
                    String.format("/atlanta/%s/%s", locationId, query));

        } catch (Exception e) {
            searchCallback.invokeFailure();
        }
    }

    public void searchSpecificCategory(
            TokenModel token,
            String query,
            RestCallback<SearchModel> searchCallback,
            String locationid) {

        try {

            searchRestManager.getRequest(
                    token.token,
                    RestEndpoints.SearchSpecificCategory,
                    searchCallback,
                    String.format("/atlanta/%s/%s", locationid, query));

        } catch (Exception e) {
            searchCallback.invokeFailure();
        }
    }
}
