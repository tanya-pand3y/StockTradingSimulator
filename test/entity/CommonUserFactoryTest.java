package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommonUserFactoryTest {

    private CommonUserFactory userFactory;

    @BeforeEach
    void setUp() {
        userFactory = new CommonUserFactory();
    }

    @Test
    void testCreateUser() {
        // Arrange
        String name = "testUser";
        String password = "testPassword";
        LocalDateTime ltd = LocalDateTime.now();

        // Act
        User user = userFactory.create(name, password, ltd);

        // Assert
        assertNotNull(user, "The created user should not be null");
        assertEquals(name, user.getName(), "The username should match");
        assertEquals(password, user.getPassword(), "The password should match");
        assertEquals(ltd, user.getCreationTime(), "The creation time should match");
    }
}
