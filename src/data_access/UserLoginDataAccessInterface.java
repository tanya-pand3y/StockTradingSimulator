package data_access;

import entity.User;

public interface UserLoginDataAccessInterface {
    boolean existByName(String Identifier);

    void save(User user);

    User get(String username);

    boolean existsByName(String username);
}