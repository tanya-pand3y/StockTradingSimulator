package use_case.login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginOutputDataTest {
    @Test
    public void testConstructorSetsUsername() {
        String expectedUsername = "testUser";
        LoginOutputData loginOutputData = new LoginOutputData(expectedUsername, false);
        assertEquals(expectedUsername, loginOutputData.getUsername());
    }

    @Test
    public void testNullUsername() {
        LoginOutputData loginOutputData = new LoginOutputData(null, false);
        assertNull(loginOutputData.getUsername());
    }
}