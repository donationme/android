package com.github.sadjz.models.login;

public enum RestEndpoints {
    Account("api/account"),
    Token("api/token");

    private String endpointPath;

    RestEndpoints(String endpointPath) {
        this.endpointPath = endpointPath;
    }

    public String getEndpointPath() {
        return this.endpointPath;
    }


}
