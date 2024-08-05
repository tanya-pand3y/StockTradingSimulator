package entity;

import java.time.LocalDateTime;

public class CommonUserFactory implements UserFactory {
    /**
     * Requires: password is valid.
     *
     * @param name
     * @param password
     * @return
     */

    /**
     * Creates and returns a user given parameters
     * @param name the username
     * @param password the password
     * @param ltd the current time
     * @return the user
     */
    @Override
    public entity.User create(String name, String password, LocalDateTime ltd) {
        return new CommonUser(name, password, ltd);
    }
}