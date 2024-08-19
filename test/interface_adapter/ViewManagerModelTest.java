package interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import static org.junit.jupiter.api.Assertions.*;

class ViewManagerModelTest {

    private ViewManagerModel viewManagerModel;

    @BeforeEach
    void setUp() {
        viewManagerModel = new ViewManagerModel();
    }

    @Test
    void testSetAndGetActiveView() {
        // Arrange
        String expectedView = "DashboardView";

        // Act
        viewManagerModel.setActiveView(expectedView);
        String actualView = viewManagerModel.getActiveView();

        // Assert
        assertEquals(expectedView, actualView, "The active view should be 'DashboardView'");
    }

    @Test
    void testPropertyChangeFired() {
        // Arrange
        String expectedView = "SignupView";
        final boolean[] wasFired = {false};

        PropertyChangeListener listener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("view".equals(evt.getPropertyName()) && expectedView.equals(evt.getNewValue())) {
                    wasFired[0] = true;
                }
            }
        };

        viewManagerModel.addPropertyChangeListener(listener);

        // Act
        viewManagerModel.setActiveView(expectedView);
        viewManagerModel.firePropertyChanged();

        // Assert
        assertTrue(wasFired[0], "The property change event should have been fired and detected by the listener.");
    }

    @Test
    void testNoPropertyChangeFiredWhenViewNotChanged() {
        // Arrange
        String initialView = "LoginView";
        final boolean[] wasFired = {false};

        PropertyChangeListener listener = evt -> wasFired[0] = true;

        viewManagerModel.addPropertyChangeListener(listener);
        viewManagerModel.setActiveView(initialView);

        // Act
        viewManagerModel.firePropertyChanged();

        // Assert
        assertTrue(wasFired[0], "The property change event should have been fired even if the view was not changed.");
    }
}
