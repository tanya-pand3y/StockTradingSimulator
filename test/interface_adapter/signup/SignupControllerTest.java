package interface_adapter.signup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupInputBoundary;

import static org.mockito.Mockito.*;

class SignupControllerTest {


    private SignupInputBoundary mockSignupInteractor;
    private SignupController signupController;

    @BeforeEach
    void setUp() {
        // Mock the SignupInputBoundary interface
        mockSignupInteractor = mock(SignupInputBoundary.class);

        // Initialize the SignupController with the mock interactor
        signupController = new SignupController(mockSignupInteractor);
    }

    @Test
    void testExecuteCallsInteractor() {
        // Arrange
        String username = "testUser";
        String password1 = "password123";
        String password2 = "password123";

        // Act
        signupController.execute(username, password1, password2);

        // Assert
        // Verify that the interactor's execute method was called once with any instance of SignupInputData
        verify(mockSignupInteractor, times(1)).execute(any());
    }
}