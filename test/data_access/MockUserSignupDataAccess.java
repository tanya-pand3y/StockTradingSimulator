package data_access;

import entity.User;
import use_case.signup.UserSignupDataAccessInterface;

public class MockUserSignupDataAccess implements data_access.UserSignupDataAccessInterface {
    private boolean userExists;
    private boolean saveCalled;

    // Set whether the user exists
    public void setUserExists(boolean userExists) {
        this.userExists = userExists;
    }

    // Check if the save method was called
    public boolean isSaveCalled() {
        return saveCalled;
    }
//
//    public boolean existsByName(User user) {
//        return userExists;
//    }

    @Override
    public boolean existsByName(String identifier) {
        return false;
    }

    @Override
    public void save(User user) {
        saveCalled = true;
    }
}
