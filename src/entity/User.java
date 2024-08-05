package entity;

import java.time.LocalDateTime;

public interface User {

    /**
     * Returns the username
     * @return the username
     */
    String getName();

    /**
     * Returns the password
     * @return the password
     */
    String getPassword();

    /**
     * Returns the creation time
     * @return the creation time
     */
    LocalDateTime getCreationTime();
}
