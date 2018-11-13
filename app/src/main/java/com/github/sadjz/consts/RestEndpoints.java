package com.github.sadjz.consts;

/**
 * The enum Rest endpoints.
 */
public enum RestEndpoints {
    /**
     * Account rest endpoints.
     */
    Account("api/account"),
    /**
     * Token rest endpoints.
     */
    Token("api/token"),
    /**
     * Region rest endpoints.
     */
    Region("api/region"),
    /**
     * Add donation item rest endpoints.
     */
    AddDonationItem("api/donationItem/add"),
    /**
     * Edit donation item rest endpoints.
     */
    EditDonationItem("api/donationItem/edit"),
    /**
     * Remove donation item rest endpoints.
     */
    RemoveDonationItem("api/donationItem/remove"),
    /**
     * Search all name rest endpoints.
     */
    SearchAllName("api/search/all/name"),
    /**
     * Search all category rest endpoints.
     */
    SearchAllCategory("api/search/all/category"),
    /**
     * Search specific name rest endpoints.
     */
    SearchSpecificName("api/search/specific/name"),
    /**
     * Search specific category rest endpoints.
     */
    SearchSpecificCategory("api/search/specific/category");
    private final String endpointPath;

    RestEndpoints(String endpointPath) {
        this.endpointPath = endpointPath;
    }

    /**
     * Gets endpoint path.
     *
     * @return the endpoint path
     */
    public String getEndpointPath() {
        return this.endpointPath;
    }
}
