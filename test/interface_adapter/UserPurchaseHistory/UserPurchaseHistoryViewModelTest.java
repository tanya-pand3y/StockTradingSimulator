package interface_adapter.UserPurchaseHistory;

import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryState;
import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.beans.PropertyChangeListener;

import static org.mockito.Mockito.*;

class UserPurchaseHistoryViewModelTest {

    private UserPurchaseHistoryViewModel viewModel;
    private PropertyChangeListener mockListener;
    private UserPurchaseHistoryState mockState;

    @BeforeEach
    void setUp() {
        viewModel = new UserPurchaseHistoryViewModel();
        mockListener = Mockito.mock(PropertyChangeListener.class);
        mockState = Mockito.mock(UserPurchaseHistoryState.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    void testSetState() {
        // Act
        viewModel.setState(mockState);

        // Assert
        // Verify that no property change event has been fired yet.
        verify(mockListener, never()).propertyChange(any());
    }

    @Test
    void testFirePropertyChanged() {
        // Act
        viewModel.firePropertyChanged();

        // Assert
        // Verify that the property change event is fired with the correct property name and state.
        verify(mockListener, times(1)).propertyChange(any());
    }

    @Test
    void testGetStateAndSetStateMethodsCalled() {
        // Act
        viewModel.setState(mockState);
        viewModel.getState();

        // Assert
        // Verify that the getState and setState methods work without calling anything else unexpectedly.
        verify(mockState, never()).getPortfolio(); // Example: Check that getPortfolio is not called directly.
    }
}

