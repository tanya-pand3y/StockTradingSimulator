package data_access;

import entity.User;

public interface UserSignupDataAccessInterface {
    /**
     * Checks if the entry exists given an identifier
     * @param identifier the identifier
     * @return whether the entry with that identifier exists
     */
    boolean existsByName(String identifier);

    /**
     * Save the user data to file
     * @param user the user
     */
    void save(User user);
}
