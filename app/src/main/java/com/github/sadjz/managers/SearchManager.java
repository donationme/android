package com.github.sadjz.managers;

import com.github.sadjz.consts.RestEndpoints;
import com.github.sadjz.datastructures.RestCallback;
import com.github.sadjz.models.login.TokenModel;
import com.github.sadjz.models.search.SearchModel;

/**
 * The type Search manager.
 */
public class SearchManager {

    private final RestManager searchRestManager = new RestManager();

    /**
     * Abort search.
     */
    public void abortSearch() {
        this.searchRestManager.abortRequest();
    }

    /**
     * Search all name.
     *
     * @param token          the token
     * @param query          the query
     * @param searchCallback the search callback
     */
    public void searchAllName(
            TokenModel token,
            String query,
            RestCallback<SearchModel> searchCallback) {

        try {

            searchRestManager.getRequest(
                    token.getToken(),
                    RestEndpoints.SearchAllName,
                    searchCallback,
                    String.format("/atlanta/%s", query));

        } catch (Exception e) {
            searchCallback.invokeFailure();
        }
    }

    /**
     * Search all category.
     *
     * @param token          the token
     * @param query          the query
     * @param searchCallback the search callback
     */
    public void searchAllCategory(
            TokenModel token,
            String query,
            RestCallback<SearchModel> searchCallback) {

        try {

            searchRestManager.getRequest(
                    token.getToken(),
                    RestEndpoints.SearchAllCategory,
                    searchCallback,
                    String.format("/atlanta/%s", query));

        } catch (Exception e) {
            searchCallback.invokeFailure();
        }
    }

    /**
     * Search specific name.
     *
     * @param token          the token
     * @param query          the query
     * @param searchCallback the search callback
     * @param locationId     the location id
     */
    public void searchSpecificName(
            TokenModel token,
            String query,
            RestCallback<SearchModel> searchCallback,
            String locationId) {

        try {

            searchRestManager.getRequest(
                    token.getToken(),
                    RestEndpoints.SearchSpecificName,
                    searchCallback,
                    String.format("/atlanta/%s/%s", locationId, query));

        } catch (Exception e) {
            searchCallback.invokeFailure();
        }
    }

    /**
     * Search specific category.
     *
     * @param token          the token
     * @param query          the query
     * @param searchCallback the search callback
     * @param locationid     the locationid
     */
    public void searchSpecificCategory(
            TokenModel token,
            String query,
            RestCallback<SearchModel> searchCallback,
            String locationid) {

        try {

            searchRestManager.getRequest(
                    token.getToken(),
                    RestEndpoints.SearchSpecificCategory,
                    searchCallback,
                    String.format("/atlanta/%s/%s", locationid, query));

        } catch (Exception e) {
            searchCallback.invokeFailure();
        }
    }
}
