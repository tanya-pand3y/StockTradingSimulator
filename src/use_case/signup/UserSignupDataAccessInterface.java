package use_case.signup;

import entity.User;

public interface UserSignupDataAccessInterface {
    boolean existsByName(User user);

    void save(User user);
}

