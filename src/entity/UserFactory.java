package src.entity;

import java.time.LocalDateTime;

public interface UserFactory {
    /**
     * Requires: password is valid.
     */
    src.entity.User create(String name, String password, LocalDateTime ltd);
}
