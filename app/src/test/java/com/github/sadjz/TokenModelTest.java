package com.github.sadjz;

//Written by Jonathan Leo

import com.github.sadjz.models.login.TokenModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class TokenModelTest {

    @Test
    public void testIllegalTokenArgumentException() {
        try {
            TokenModel test = new TokenModel(null);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Token cannot be null");
        }
    }

    @Test
    public void testCorrect() {
        String testString = "This is a test token";
        TokenModel test = new TokenModel(testString);
        assertEquals(test.getToken(), testString);
    }
}
