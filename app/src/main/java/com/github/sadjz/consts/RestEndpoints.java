package com.github.sadjz.consts;

public enum RestEndpoints {
    Account("api/account"),
    Token("api/token"),
    Region("api/region"),
    AddDonationItem("api/donationItem/add"),
    EditDonationItem("api/donationItem/edit"),
    RemoveDonationItem("api/donationItem/remove"),
    SearchAllName("api/search/all/name"),
    SearchAllCategory("api/search/all/category"),
    SearchSpecificName("api/search/specific/name"),
    SearchSpecificCategory("api/search/specific/category");
    private final String endpointPath;

    RestEndpoints(String endpointPath) {
        this.endpointPath = endpointPath;
    }

    public String getEndpointPath() {
        return this.endpointPath;
    }
}
