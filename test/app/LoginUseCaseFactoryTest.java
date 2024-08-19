package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.LoginView;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class LoginUseCaseFactoryTest {

    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private DashboardViewModel dashboardViewModel;
    private SignupViewModel signupViewModel;

    @BeforeEach
    void setUp() {
        // Mock the dependencies
        viewManagerModel = mock(ViewManagerModel.class);
        loginViewModel = mock(LoginViewModel.class);
        dashboardViewModel = mock(DashboardViewModel.class);
        signupViewModel = mock(SignupViewModel.class);
    }

    @Test
    void testCreateLoginViewNotNull() {
        // Act
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, dashboardViewModel, signupViewModel);

        // Assert
        assertNotNull(loginView, "The created LoginView should not be null");
    }
}
