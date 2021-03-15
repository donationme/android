package com.github.sadjz.models.user;

import com.google.gson.annotations.SerializedName;

/**
 * The type User model.
 */
public class UserModel {

    @SerializedName("name")
    private final String name;

    @SerializedName("email")
    private final String email;

    @SerializedName("type")
    private final UserType type;

    /**
     * Instantiates a new User model.
     *
     * @param name  the name
     * @param email the email
     * @param type  the type
     */
    public UserModel(String name, String email, UserType type) {
        this.name = name;
        this.email = email;
        this.type = type;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public UserType getType() {
        return this.type;
    }
}
