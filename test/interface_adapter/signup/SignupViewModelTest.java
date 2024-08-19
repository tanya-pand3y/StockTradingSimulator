package interface_adapter.signup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SignupViewModelTest {


    private SignupViewModel signupViewModel;

    @BeforeEach
    void setUp() {
        signupViewModel = new SignupViewModel();
    }

    @Test
    void testSetState() {
        // Arrange
        SignupState newState = new SignupState();
        newState.setUsername("newUser");

        // Act
        signupViewModel.setState(newState);

        // Assert
        assertEquals("newUser", signupViewModel.getState().getUsername());
    }

    @Test
    void testFirePropertyChanged() {
        // Arrange
        PropertyChangeListener mockListener = mock(PropertyChangeListener.class);
        signupViewModel.addPropertyChangeListener(mockListener);

        // Act
        signupViewModel.firePropertyChanged();

        // Assert
        // Verify that the property change event is fired
        verify(mockListener, times(1)).propertyChange(any());
    }

    @Test
    void testAddPropertyChangeListener() {
        // Arrange
        PropertyChangeListener mockListener = mock(PropertyChangeListener.class);

        // Act
        signupViewModel.addPropertyChangeListener(mockListener);
        signupViewModel.firePropertyChanged();

        // Assert
        // Verify that the listener is called when the property changes
        verify(mockListener, times(1)).propertyChange(any());
    }

    @Test
    void testGetState() {
        // Arrange
        SignupState state = new SignupState();
        state.setUsername("testUser");
        signupViewModel.setState(state);

        // Act & Assert
        assertEquals("testUser", signupViewModel.getState().getUsername());
    }
}