package app;

import interface_adapter.QueryStock.QueryStockViewModel;
import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.buy.BuyViewModel;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.sell.SellViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.DashboardView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DashboardViewFactoryTest {

    private ViewManagerModel viewManagerModel;
    private DashboardViewModel dashboardViewModel;
    private SellViewModel sellViewModel;
    private LoginViewModel loginViewModel;
    private UserPurchaseHistoryViewModel userPurchaseHistoryViewModel;
    private BuyViewModel buyViewModel;
    private QueryStockViewModel queryStockViewModel;

    @BeforeEach
    void setUp() {
        // Mock the dependencies
        viewManagerModel = mock(ViewManagerModel.class);
        dashboardViewModel = mock(DashboardViewModel.class);
        sellViewModel = mock(SellViewModel.class);
        loginViewModel = mock(LoginViewModel.class);
        userPurchaseHistoryViewModel = mock(UserPurchaseHistoryViewModel.class);
        buyViewModel = mock(BuyViewModel.class);
        queryStockViewModel = mock(QueryStockViewModel.class);
    }

    @Test
    void testCreateDashboardViewNotNull() {
        // Act
        DashboardView dashboardView = DashboardViewFactory.create(
                viewManagerModel,
                dashboardViewModel,
                sellViewModel,
                loginViewModel,
                userPurchaseHistoryViewModel,
                buyViewModel,
                queryStockViewModel
        );

        // Assert
        assertNotNull(dashboardView, "The created DashboardView should not be null");
    }
}