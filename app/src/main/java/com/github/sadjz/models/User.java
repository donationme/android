package com.github.sadjz.models;


/**
 * Created by afshawnlotfi on 2/22/18.
 *
 * <<Information Holder>>
 * Maintains information about the User class
 */

public class User {
    private String _username;
    private String _password;

    /**
     * Make a new User
     * @param uid  the user id for login
     * @param pwd  the password for login
     */
    public User (String uid, String pwd, String name) {
        _username = uid;
        _password = pwd;

    }

    /**
     * Check that the provided password matches
     * @param pwd the provided password
     * @return true if an exact string match for password, false otherwise
     */
    boolean checkPassword(String pwd) {
        return _password.equals(pwd);
    }

    /**
     * get the actual user name
     *
     * @return the actual name of the user
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("user: ");
        return sb.toString();
    }

}
