package com.github.sadjz;
import com.github.sadjz.models.user.UserModel;
import org.junit.Test;
import static org.junit.Assert.*;
import com.github.sadjz.models.user.UserType;

/**
 *A unit test for the UserModel file in the client side of the Android app
 */

public class UserModelTest {

    @Test
    public void testIllegalFirstStringArgument() {
        try {
            UserModel user = new UserModel(null, "email", UserType.Admin);

        } catch (IllegalArgumentException e) {
            assertEquals("The user name argument is invalid", e.getMessage());
        }
    }

    @Test
    public void testIllegalSecondStringArgument() {
        try {
            UserModel user = new UserModel("name", null, UserType.Admin);
        } catch (IllegalArgumentException e) {
            assertEquals("The email name argument is invalid", e.getMessage());
        }
    }

    @Test
    public void testIllegalUserTypeArgument() {
        try {
            UserModel user = new UserModel("name", "email", null);
        } catch (IllegalArgumentException e) {
            assertEquals("The user type argument is invalid", e.getMessage());
        }
    }

    @Test
    public void testIllegalArguments() {
        try {
            UserModel user = new UserModel(null, null, null);
        } catch (IllegalArgumentException e) {
            assertEquals("The user model object is invalid", e.getMessage());
        }
    }

    @Test
    public void testCorrectModel() {
        String name = "name";
        String email = "email";
        UserModel user = new UserModel("name", "email", UserType.Admin);

        assertEquals(user.getName(), name);
        assertEquals(user.getEmail(), email);
        assertEquals(user.getType(), UserType.Admin);
    }
}