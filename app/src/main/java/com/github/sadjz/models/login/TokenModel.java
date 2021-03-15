package com.github.sadjz.models.login;

import com.google.gson.annotations.SerializedName;

/**
 * The type Token model.
 */
@SuppressWarnings("ClassWithTooManyDependents")
public class TokenModel {

    /**
     * The Token.
     */
    @SerializedName("token")
    private final String token;

    /**
     * Instantiates a new Token model.
     *
     * @param token the token
     */
    @SuppressWarnings("unused")
    public TokenModel(String token){
        if (token == null) {
            throw new IllegalArgumentException("Token cannot be null");
        }
        this.token = token;
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return this.token;
    }


}
