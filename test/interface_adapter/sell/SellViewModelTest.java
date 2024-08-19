package interface_adapter.sell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.mockito.Mockito.*;

class SellViewModelTest {

    private SellViewModel sellViewModel;
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        // Initialize SellViewModel instance
        sellViewModel = new SellViewModel();

        // Mock the PropertyChangeListener
        mockListener = mock(PropertyChangeListener.class);

        // Add the mock listener to the view model
        sellViewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    void testFirePropertyChanged() {
        // Act
        sellViewModel.firePropertyChanged();

        // Assert
        // Verify that the property change listener was notified
        verify(mockListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void testSetStateAndFirePropertyChanged() {
        // Arrange
        SellState newState = new SellState();
        newState.setQuantity(10);

        // Act
        sellViewModel.setState(newState);
        sellViewModel.firePropertyChanged();

        // Assert
        // Verify that the property change listener was notified with the updated state
        verify(mockListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
        // Verify that the state was correctly set
        assert(sellViewModel.getState().getQuantity() == 10);
    }
}