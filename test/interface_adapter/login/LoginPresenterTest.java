package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardState;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.login.LoginOutputData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginPresenterTest {

    private LoginPresenter loginPresenter;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private DashboardViewModel dashboardViewModel;
    private SignupViewModel signupViewModel;

    @BeforeEach
    void setUp() {
        // Mock the dependent view models
        viewManagerModel = Mockito.mock(ViewManagerModel.class);
        loginViewModel = Mockito.mock(LoginViewModel.class);
        dashboardViewModel = Mockito.mock(DashboardViewModel.class);
        signupViewModel = Mockito.mock(SignupViewModel.class);

        // Create the LoginPresenter with the mocked dependencies
        loginPresenter = new LoginPresenter(viewManagerModel, loginViewModel, dashboardViewModel, signupViewModel);
    }


    @Test
    void prepareSuccessView() {
        // Arrange
        // Create a LoginOutputData object representing the response from the interactor
        LoginOutputData response = new LoginOutputData("testUser", false);

        // Mock the DashboardState and LoginState objects
        DashboardState dashboardState = new DashboardState();
        LoginState loginState = new LoginState();

        // Mock the behavior of getting the state from the view models
        when(dashboardViewModel.getState()).thenReturn(dashboardState);
        when(loginViewModel.getState()).thenReturn(loginState);

        // Act
        loginPresenter.prepareSuccessView(response);

        // Assert
        // Verify that the dashboard state is updated correctly
        assertEquals("testUser", dashboardState.getUsername());

        // Verify that the login state is updated correctly
        assertEquals("testUser", loginState.getUsername());

        // Verify that the setState and firePropertyChanged methods are called on the view models
        verify(dashboardViewModel, times(1)).setState(dashboardState);
        verify(dashboardViewModel, times(1)).firePropertyChanged();

        verify(loginViewModel, times(1)).setState(loginState);
        verify(loginViewModel, times(1)).firePropertyChanged();

        // Verify that the view manager switches to the dashboard view and fires a property change
        verify(viewManagerModel, times(1)).setActiveView(dashboardViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    void prepareFailView() {
        // Arrange
        String errorMessage = "Invalid credentials";
        LoginState loginState = new LoginState(); // Create an instance of LoginState

        // Mock the behavior of getting the state from the login view model
        when(loginViewModel.getState()).thenReturn(loginState);

        // Act
        loginPresenter.prepareFailView(errorMessage);

        // Verify that firePropertyChanged is called on the login view model
        verify(loginViewModel, times(1)).firePropertyChanged();
    }

    @Test
    void prepareSignUpView() {
        // Act
        loginPresenter.prepareSignUpView();

        // Assert
        // Verify that the active view is switched to the signup view and property change is fired
        verify(viewManagerModel, times(1)).setActiveView(signupViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }
}