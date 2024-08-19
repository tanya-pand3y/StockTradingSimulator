package app;

import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class SignupUseCaseFactoryTest {

    private ViewManagerModel viewManagerModel;
    private SignupViewModel signupViewModel;
    private DashboardViewModel dashboardViewModel;

    @BeforeEach
    void setUp() {
        // Mock the dependencies
        viewManagerModel = mock(ViewManagerModel.class);
        signupViewModel = mock(SignupViewModel.class);
        dashboardViewModel = mock(DashboardViewModel.class);
    }

    @Test
    void testCreateSignupViewNotNull() {
        // Act
        SignupView signupView = null;
        signupView = SignupUseCaseFactory.create(viewManagerModel, signupViewModel, dashboardViewModel);

        // Assert
        assertNotNull(signupView, "The created SignupView should not be null");
    }
}
