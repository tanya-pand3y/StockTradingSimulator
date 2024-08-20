package data_access;

import entity.User;

public interface UserLoginDataAccessInterface {

    /**
     * Save a users data
     * @param user the user
     */
    void save(User user);

    /**
     * Gets the user data for the username
     * @param username the username
     * @return the user data
     */
    User get(String username);

    /**
     * Checks if a user exists given a username
     * @param username the username
     * @return if a user exists with that name
     */
    boolean existsByName(String username);
}
