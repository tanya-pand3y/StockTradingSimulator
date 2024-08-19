package interface_adapter.dashboard;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class DashboardViewModelTest {

    private DashboardViewModel dashboardViewModel;

    @BeforeEach
    public void setUp() {
        dashboardViewModel = new DashboardViewModel();
    }

    @Test
    public void testInitialState() {
        DashboardState initialState = dashboardViewModel.getState();
        assertNotNull(initialState, "Initial state should not be null");
        // You might want to check the initial values of the state here if needed
    }

    @Test
    public void testSetState() {
        DashboardState newState = new DashboardState();
        dashboardViewModel.setState(newState);
        assertEquals(newState, dashboardViewModel.getState(), "State should be updated correctly");
    }

    @Test
    public void testFirePropertyChanged() {
        PropertyChangeListener mockListener = mock(PropertyChangeListener.class);
        dashboardViewModel.addPropertyChangeListener(mockListener);

        DashboardState newState = new DashboardState();
        dashboardViewModel.setState(newState);

        dashboardViewModel.firePropertyChanged();

        verify(mockListener).propertyChange(any(PropertyChangeEvent.class));
    }

}
