package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;


class CommonUserTest {
    private CommonUser commonUser;

    @BeforeEach
    void init() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        commonUser = new CommonUser("Tanya", "password", LocalDateTime.parse("2024-08-18T00:00:00", formatter));
    }

    @Test
    void getName() {
        assertEquals("Tanya", commonUser.getName());
    }

    @Test
    void getPassword() {
        assertEquals("password", commonUser.getPassword());
    }

    @Test
    void getCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        assertEquals(commonUser.getCreationTime(), LocalDateTime.parse("2024-08-18T00:00:00", formatter));
    }

}
