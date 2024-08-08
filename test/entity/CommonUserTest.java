package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserTest {
    private CommonUser commonUser;

    @BeforeEach
    void init() {
        commonUser = new CommonUser("Tanya", "password", LocalDateTime.now());
    }

    @Test
    void getName() {
        assertEquals("Tanya", commonUser.getName());
    }

    @Test
    void getPassword() {
        assertEquals("password", commonUser.getPassword());
    }

}
