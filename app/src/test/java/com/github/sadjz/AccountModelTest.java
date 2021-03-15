package com.github.sadjz;

//written by Zida Wang

import com.github.sadjz.models.account.AccountModel;
import org.junit.Test;
import com.github.sadjz.models.login.LoginModel;
import com.github.sadjz.models.user.UserModel;
import com.github.sadjz.models.user.UserType;
import static org.junit.Assert.*;

public class AccountModelTest {

    @Test
    public void testIllegalUserModelArgumentException() {
        try {
            AccountModel account = new AccountModel(new LoginModel("user", "password"), null);
        } catch (IllegalArgumentException e) {
            assertEquals("The user model argument is invalid", e.getMessage());
        }
    }

    @Test
    public void testIllegalLoginModelArgumentException() {
        try {
            AccountModel account = new AccountModel(null, new UserModel("name", "email", UserType.User));
        } catch (IllegalArgumentException e) {
            assertEquals("The login model argument is invalid", e.getMessage());
        }
    }

    @Test
    public void testBothIllegalArgumentException() {
        try {
            AccountModel account = new AccountModel(null, null);
        } catch (IllegalArgumentException e) {
            assertEquals("Both login model and user model arguments are invalid", e.getMessage());
        }
    }

    @Test
    public void testCorrect() {
        LoginModel testLogin = new LoginModel("user", "password");
        UserModel testUser = new UserModel("name", "email", UserType.User);
        AccountModel account = new AccountModel(testLogin, testUser);
        assertEquals(account.getLoginModel(), testLogin);
        assertEquals(account.getUserModel(), testUser);
    }
}
