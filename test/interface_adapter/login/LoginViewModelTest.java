package interface_adapter.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginViewModelTest {

    private LoginViewModel loginViewModel;
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        // Initialize the LoginViewModel before each test
        loginViewModel = new LoginViewModel();

        // Mock a PropertyChangeListener
        mockListener = mock(PropertyChangeListener.class);

        // Add the mock listener to the ViewModel
        loginViewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    void testGetState() {
        // Test that the initial state is as expected
        LoginState initialState = loginViewModel.getState();
        assertNotNull(initialState);
        assertEquals("", initialState.getUsername());
        assertNull(initialState.getUsernameError());
    }

    @Test
    void testSetState() {
        // Arrange
        LoginState newState = new LoginState();
        newState.setUsername("newUser");

        // Act
        loginViewModel.setState(newState);

        // Assert
        assertEquals("newUser", loginViewModel.getState().getUsername());
    }

    @Test
    void testFirePropertyChanged() {
        // Arrange
        LoginState newState = new LoginState();
        newState.setUsername("newUser");

        loginViewModel.setState(newState);

        // Act
        loginViewModel.firePropertyChanged();

        // Assert
        // Verify that the listener receives a property change event
        verify(mockListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void testAddPropertyChangeListener() {
        // Arrange
        PropertyChangeListener newListener = mock(PropertyChangeListener.class);
        loginViewModel.addPropertyChangeListener(newListener);

        // Act
        loginViewModel.firePropertyChanged();

        // Assert
        // Verify that both listeners receive a property change event
        verify(mockListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
        verify(newListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }
}