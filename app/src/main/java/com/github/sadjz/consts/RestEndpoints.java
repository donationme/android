package com.github.sadjz.consts;

public enum RestEndpoints {
    Account("api/account"),
    Token("api/token"),
    Location("api/location"),
    AddDonationItem("api/donationitem/add"),
    EditDonationItem("api/donationitem/edit"),
    RemoveDonationItem("api/donationitem/remove"),
    SearchAllName("api/search/all/name"),
    SearchAllCategory("api/search/all/category"),
    SearchSpecificName("api/search/specific/name"),
    SearchSpecificCategory("api/search/specific/category");
    private String endpointPath;

    RestEndpoints(String endpointPath) {
        this.endpointPath = endpointPath;
    }

    public String getEndpointPath() {
        return this.endpointPath;
    }


}
