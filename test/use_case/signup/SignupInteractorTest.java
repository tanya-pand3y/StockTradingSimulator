package use_case.signup;

import data_access.StockQuantityDataAccessInterface;
import data_access.UserSignupDataAccessInterface;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SignupInteractorTest {
    
    @Test
    public void testExecuteUserAlreadyExist() {
        // Arrange
        UserSignupDataAccessInterface userSignupDataAccessInterface = Mockito.mock(UserSignupDataAccessInterface.class);
        SignupOutputBoundary signupOutputBoundary = Mockito.mock(SignupOutputBoundary.class);
        UserFactory userFactory = Mockito.mock(UserFactory.class);
        StockQuantityDataAccessInterface stockQuantityDataAccessObject = Mockito.mock(StockQuantityDataAccessInterface.class);
        SignupInteractor interactor = new SignupInteractor(userSignupDataAccessInterface, signupOutputBoundary, userFactory, stockQuantityDataAccessObject);
        SignupInputData inputData = new SignupInputData("testUser", "testPassword", "testPassword");

        when(userSignupDataAccessInterface.existsByName(inputData.getUsername())).thenReturn(true);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(signupOutputBoundary).prepareFailView("User already exists.");
    }

    @Test
    public void testExecutePasswordMismatch() {
        // Arrange
        UserSignupDataAccessInterface userSignupDataAccessInterface = Mockito.mock(UserSignupDataAccessInterface.class);
        SignupOutputBoundary signupOutputBoundary = Mockito.mock(SignupOutputBoundary.class);
        UserFactory userFactory = Mockito.mock(UserFactory.class);
        StockQuantityDataAccessInterface stockQuantityDataAccessObject = Mockito.mock(StockQuantityDataAccessInterface.class);
        SignupInteractor interactor = new SignupInteractor(userSignupDataAccessInterface, signupOutputBoundary, userFactory, stockQuantityDataAccessObject);
        SignupInputData inputData = new SignupInputData("testUser", "testPassword", "testPasswordMismatch");

        // Act
        interactor.execute(inputData);

        // Assert
        verify(signupOutputBoundary).prepareFailView("Passwords don't match.");
    }
}