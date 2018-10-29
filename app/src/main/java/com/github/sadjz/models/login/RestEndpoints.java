package com.github.sadjz.models.login;

public enum RestEndpoints {
    Account("api/account"),
    Token("api/token"),
    Location("api/location/atlanta"),
    AddDonationItem("api/donationitem/add/atlanta"),
    EditDonationItem("api/donationitem/edit/atlanta"),
    RemoveDonationItem("api/donationitem/remove/atlanta"),
    SearchName("api/search/name/atlanta"),
    SearchCategory("api/search/category/atlanta");
    private String endpointPath;

    RestEndpoints(String endpointPath) {
        this.endpointPath = endpointPath;
    }

    public String getEndpointPath() {
        return this.endpointPath;
    }


}
