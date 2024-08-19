package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardState;
import interface_adapter.dashboard.DashboardViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupOutputData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SignupPresenterTest {

    private ViewManagerModel mockViewManagerModel;
    private SignupViewModel mockSignupViewModel;
    private DashboardViewModel mockDashboardViewModel;
    private SignupPresenter signupPresenter;

    @BeforeEach
    void setUp() {
        mockViewManagerModel = mock(ViewManagerModel.class);
        mockSignupViewModel = mock(SignupViewModel.class);
        mockDashboardViewModel = mock(DashboardViewModel.class);

        signupPresenter = new SignupPresenter(mockViewManagerModel, mockSignupViewModel, mockDashboardViewModel);
    }

    @Test
    void testPrepareSuccessView() {
        // Arrange
        SignupOutputData mockResponse = mock(SignupOutputData.class);
        when(mockResponse.getCreationTime()).thenReturn("2024-08-18T10:15:30");
        when(mockResponse.getUsername()).thenReturn("testUser");

        DashboardState mockDashboardState = mock(DashboardState.class);
        when(mockDashboardViewModel.getState()).thenReturn(mockDashboardState);

        // Act
        signupPresenter.prepareSuccessView(mockResponse);

        // Assert
        // Verify that the correct methods are called
        verify(mockResponse).setCreationTime(anyString()); // Check if the time was formatted
        verify(mockDashboardViewModel).setState(mockDashboardState);
        verify(mockDashboardViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView(mockDashboardViewModel.getViewName());
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    void testPrepareFailView() {
        // Arrange
        String errorMessage = "Signup failed";

        SignupState mockSignupState = mock(SignupState.class);
        when(mockSignupViewModel.getState()).thenReturn(mockSignupState);

        // Act
        signupPresenter.prepareFailView(errorMessage);

        // Assert
        // Verify that the correct methods are called
        verify(mockSignupState).setUsernameError(errorMessage);
        verify(mockSignupViewModel).firePropertyChanged();
    }
}