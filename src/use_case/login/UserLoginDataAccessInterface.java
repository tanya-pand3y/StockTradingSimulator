package use_case.login;

import src.entity.User;

public interface UserLoginDataAccessInterface {
//    boolean existByEmail(String identifier);
    boolean existByName(String identifier);
    void save(User user);

    User get(String username);
}
