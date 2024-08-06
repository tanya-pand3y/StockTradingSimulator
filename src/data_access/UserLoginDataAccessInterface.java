package data_access;

import entity.User;

public interface UserLoginDataAccessInterface {

    void save(User user);

    User get(String username);

    boolean existsByName(String username);
}
