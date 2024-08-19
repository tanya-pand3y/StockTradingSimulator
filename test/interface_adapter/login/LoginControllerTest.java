package interface_adapter.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class LoginControllerTest {

    private LoginController loginController;
    private LoginInputBoundary loginUseCaseInteractor;

    @BeforeEach
    void setUp() {
        // Mock the LoginInputBoundary interface
        loginUseCaseInteractor = Mockito.mock(LoginInputBoundary.class);

        // Create the LoginController with the mocked LoginInputBoundary
        loginController = new LoginController(loginUseCaseInteractor);
    }

    @Test
    void testExecute() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";

        // Act
        loginController.execute(username, password);

        // Assert
        // Verify that the execute method of the interactor is called with the correct input data
        verify(loginUseCaseInteractor, times(1)).execute(any(LoginInputData.class));
    }

    @Test
    void testSignUpClicked() {
        // Act
        loginController.signUpClicked();

        // Assert
        // Verify that the prepareSignUpView method is called on the interactor
        verify(loginUseCaseInteractor, times(1)).prepareSignUpView();
    }

}