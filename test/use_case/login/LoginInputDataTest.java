package use_case.login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginInputDataTest {

    public void testConstructorSetsUsername() {
        // Arrange
        String expectedUsername = "testUser";
        String password = "password123";

        // Act
        LoginInputData loginInputData = new LoginInputData(expectedUsername, password);

        // Assert
        assertEquals(expectedUsername, loginInputData.getUsername());
    }

    @Test
    public void testConstructorSetsPassword() {
        // Arrange
        String username = "testUser";
        String expectedPassword = "password123";

        // Act
        LoginInputData loginInputData = new LoginInputData(username, expectedPassword);

        // Assert
        assertEquals(expectedPassword, loginInputData.getPassword());
    }

    @Test
    public void testGetUsernameAndPassword() {
        // Arrange
        String expectedUsername = "testUser";
        String expectedPassword = "password123";

        // Act
        LoginInputData loginInputData = new LoginInputData(expectedUsername, expectedPassword);

        // Assert
        assertEquals(expectedUsername, loginInputData.getUsername());
        assertEquals(expectedPassword, loginInputData.getPassword());
    }

    @Test
    public void testUsernameIsImmutable() {
        // Arrange
        String originalUsername = "testUser";
        String password = "password123";
        LoginInputData loginInputData = new LoginInputData(originalUsername, password);

        // Act
        String retrievedUsername = loginInputData.getUsername();
        retrievedUsername = "newUsername";

        // Assert
        assertNotEquals(retrievedUsername, loginInputData.getUsername());
        assertEquals(originalUsername, loginInputData.getUsername());
    }

    @Test
    public void testPasswordIsImmutable() {
        // Arrange
        String username = "testUser";
        String originalPassword = "password123";
        LoginInputData loginInputData = new LoginInputData(username, originalPassword);

        // Act
        String retrievedPassword = loginInputData.getPassword();
        retrievedPassword = "newPassword";

        // Assert
        assertNotEquals(retrievedPassword, loginInputData.getPassword());
        assertEquals(originalPassword, loginInputData.getPassword());
    }

    @Test
    public void testNullUsernameAndPassword() {
        // Arrange
        String username = null;
        String password = null;

        // Act
        LoginInputData loginInputData = new LoginInputData(username, password);

        // Assert
        assertNull(loginInputData.getUsername());
        assertNull(loginInputData.getPassword());
    }
}