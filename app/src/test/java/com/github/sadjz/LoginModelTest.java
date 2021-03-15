package com.github.sadjz;

import org.junit.Test;
import com.github.sadjz.models.login.LoginModel;
import static org.junit.Assert.*;

/**
 * Unit test for LoginModel constructor method.
 * Author: Drew Hatcher
 */
public class LoginModelTest {
    @Test
    public void usernameNull() {
        try {
            LoginModel test1 = new LoginModel(null, "password");
        } catch (IllegalArgumentException e){
            assertEquals("The username cannot be null.", e.getMessage());
        }
    }

    @Test
    public void passwordNull() {
        try {
            LoginModel test2 = new LoginModel("username", null);
        } catch (IllegalArgumentException e){
            assertEquals("The password cannot be null.", e.getMessage());
        }
    }

    @Test
    public void usernameAndPasswordNull() {
        try {
            LoginModel test3 = new LoginModel(null, null);
        } catch (IllegalArgumentException e){
            assertEquals("The username cannot be null.", e.getMessage());
        }
    }

    @Test
    public void usernameAndPasswordNotNull() {
        LoginModel test4 = new LoginModel("username", "password");
        String testUsername = test4.getUsername();
        String testPassword = test4.getPassword();
        assertEquals("username", testUsername);
        assertEquals("password", testPassword);
    }
}
