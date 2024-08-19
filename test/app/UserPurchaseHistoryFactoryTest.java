package app;

import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.UserPurchaseHistoryView;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class UserPurchaseHistoryFactoryTest {

    private ViewManagerModel viewManagerModel;
    private UserPurchaseHistoryViewModel userPurchaseHistoryViewModel;
    private DashboardViewModel dashboardViewModel;

    @BeforeEach
    void setUp() {
        // Mock the dependencies
        viewManagerModel = mock(ViewManagerModel.class);
        userPurchaseHistoryViewModel = mock(UserPurchaseHistoryViewModel.class);
        dashboardViewModel = mock(DashboardViewModel.class);
    }

    @Test
    void testCreateUserPurchaseHistoryViewNotNull() {
        // Act
        UserPurchaseHistoryView userPurchaseHistoryView = null;
        userPurchaseHistoryView = UserPurchaseHistoryFactory.create(viewManagerModel, userPurchaseHistoryViewModel, dashboardViewModel);


        // Assert
        assertNotNull(userPurchaseHistoryView, "The created UserPurchaseHistoryView should not be null");
    }
}
