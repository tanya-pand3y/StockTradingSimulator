package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorServiceTest {

    @Test
    void passwordIsValid() {
        PasswordValidatorService validator = new PasswordValidatorService();
        String p1 = "";
        String p2 = "abcd";
        String p3 = "ValidPassword";
        assertFalse(validator.passwordIsValid(p1));
        assertFalse(validator.passwordIsValid(p2));
        assertTrue(validator.passwordIsValid(p3));

    }
}