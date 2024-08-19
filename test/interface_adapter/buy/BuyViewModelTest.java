package interface_adapter.buy;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BuyViewModelTest {

    private BuyViewModel buyViewModel;
    private PropertyChangeListener mockListener;
    private PropertyChangeSupport mockSupport;


    @BeforeEach
    public void setUp() {
        buyViewModel = new BuyViewModel();
        mockSupport = mock(PropertyChangeSupport.class);
        mockListener = mock(PropertyChangeListener.class);
        buyViewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    public void testSetState() {
        BuyState oldState = buyViewModel.getState();
        BuyState newState = new BuyState();
        buyViewModel.setState(newState);

        verify(mockListener).propertyChange(argThat(event -> {
            return "state".equals(event.getPropertyName()) &&
                    oldState.equals(event.getOldValue()) &&
                    newState.equals(event.getNewValue());
        }));
    }


    @Test
    public void testGetState() {
        BuyState state = new BuyState();
        buyViewModel.setState(state);
        assertEquals(state, buyViewModel.getState());
    }
}
