package use_case.login;

import data_access.UserLoginDataAccessInterface;
import data_access.UserSignupDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {

    private LoginInteractor loginInteractor;
    private InMemoryUserDataAccessObject userDataAccessObject;
    private TestLoginPresenter loginPresenter;

    @BeforeEach
    void setUp() {
        userDataAccessObject = new InMemoryUserDataAccessObject();
        loginPresenter = new TestLoginPresenter();
        loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);

        // Setting up some test users
        userDataAccessObject.save(new TestUser("testUser", "correctPassword", LocalDateTime.now()));
    }

    @Test
    void testNonExistingUser() {
        LoginInputData inputData = new LoginInputData("nonExistentUser", "password");
        loginInteractor.execute(inputData);

        assertTrue(loginPresenter.isUseCaseFailed());
        assertEquals("nonExistentUser: Account does not exist.", loginPresenter.getFailMessage());
    }

    @Test
    void testExistingUserCorrectPassword() {
        LoginInputData inputData = new LoginInputData("testUser", "correctPassword");
        loginInteractor.execute(inputData);

        assertFalse(loginPresenter.isUseCaseFailed());
        assertEquals("testUser", loginPresenter.getSuccessOutputData().getUsername());
    }

    @Test
    void testExistingUserIncorrectPassword() {
        LoginInputData inputData = new LoginInputData("testUser", "wrongPassword");
        loginInteractor.execute(inputData);

        assertTrue(loginPresenter.isUseCaseFailed());
        assertEquals("Incorrect password for testUser.", loginPresenter.getFailMessage());
    }

    @Test
    void testMultipleLoginAttempts() {
        // Correct login attempt
        LoginInputData inputData1 = new LoginInputData("testUser", "correctPassword");
        loginInteractor.execute(inputData1);
        assertFalse(loginPresenter.isUseCaseFailed());

        // Incorrect login attempt
        LoginInputData inputData2 = new LoginInputData("testUser", "wrongPassword");
        loginInteractor.execute(inputData2);
        assertTrue(loginPresenter.isUseCaseFailed());
    }

    // Implementing an in-memory DAO for testing purposes
    private static class InMemoryUserDataAccessObject implements UserSignupDataAccessInterface, UserLoginDataAccessInterface {
        private final Map<String, User> users = new HashMap<>();

        @Override
        public void save(User user) {
            users.put(user.getName(), user);
        }

        @Override
        public User get(String username) {
            return users.get(username);
        }

        @Override
        public boolean existsByName(String username) {
            return users.containsKey(username);
        }
    }

    // A simple presenter for capturing the interactor's outputs
    private static class TestLoginPresenter implements LoginOutputBoundary {
        private boolean useCaseFailed;
        private String failMessage;
        private LoginOutputData successOutputData;

        @Override
        public void prepareFailView(String message) {
            this.useCaseFailed = true;
            this.failMessage = message;
        }

        @Override
        public void prepareSuccessView(LoginOutputData loginOutputData) {
            this.useCaseFailed = false;
            this.successOutputData = loginOutputData;
        }

        @Override
        public void prepareSignUpView() {
            // Implement as needed for your tests
        }

        public boolean isUseCaseFailed() {
            return useCaseFailed;
        }

        public String getFailMessage() {
            return failMessage;
        }

        public LoginOutputData getSuccessOutputData() {
            return successOutputData;
        }
    }
}

