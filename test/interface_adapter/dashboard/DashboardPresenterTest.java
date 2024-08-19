package interface_adapter.dashboard;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import entity.Portfolio;
import interface_adapter.QueryStock.QueryStockViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.dashboard.DashboardOutputData;
import interface_adapter.buy.BuyState;
import interface_adapter.buy.BuyViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.sell.SellState;
import interface_adapter.sell.SellViewModel;
import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryState;
import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryViewModel;
import interface_adapter.ViewManagerModel;
import view.QueryStockView;

public class DashboardPresenterTest {

    private ViewManagerModel viewManagerModel;
    private UserPurchaseHistoryViewModel userPurchaseHistoryViewModel;
    private SellViewModel sellViewModel;
    private LoginViewModel loginViewModel;
    private DashboardViewModel dashboardViewModel;
    private BuyViewModel buyViewModel;
    private QueryStockViewModel queryStockViewModel;
    private DashboardPresenter dashboardPresenter;

    @BeforeEach
    public void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        userPurchaseHistoryViewModel = mock(UserPurchaseHistoryViewModel.class);
        sellViewModel = mock(SellViewModel.class);
        loginViewModel = mock(LoginViewModel.class);
        dashboardViewModel = mock(DashboardViewModel.class);
        buyViewModel = mock(BuyViewModel.class);
        queryStockViewModel = mock(QueryStockViewModel.class);
        dashboardPresenter = new DashboardPresenter(
                viewManagerModel,
                userPurchaseHistoryViewModel,
                sellViewModel,
                loginViewModel,
                dashboardViewModel,
                buyViewModel,
                queryStockViewModel
        );
    }

    @Test
    public void testPrepareSellView() {
        DashboardOutputData dashboardOutputData = mock(DashboardOutputData.class);
        when(dashboardOutputData.getUsername()).thenReturn("testUser");
        when(dashboardOutputData.getPortfolio()).thenReturn(mock(Portfolio.class));

        SellState sellState = mock(SellState.class);
        when(sellViewModel.getState()).thenReturn(sellState);

        dashboardPresenter.prepareSellView(dashboardOutputData);

        verify(sellViewModel).setState(sellState);
        verify(sellState).setUsername("testUser");
        verify(sellState).setPortfolio(dashboardOutputData.getPortfolio());
        verify(sellViewModel).firePropertyChanged();
        verify(viewManagerModel).setActiveView(sellViewModel.getViewName());
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPrepareLogout() {
        DashboardState dashboardState = mock(DashboardState.class);
        when(dashboardViewModel.getState()).thenReturn(dashboardState);

        LoginState loginState = mock(LoginState.class);
        when(loginViewModel.getState()).thenReturn(loginState);

        dashboardPresenter.prepareLogout();

        verify(dashboardViewModel).setState(dashboardState);
        verify(dashboardState).setUsername("");
        verify(dashboardState).setPortfolio(null);
        verify(dashboardViewModel).firePropertyChanged("UserLogout");

        verify(loginViewModel).setState(loginState);
        verify(loginState).setUsername("");
        verify(loginState).setPassword("");
        verify(loginState).setLoginError(null);
        verify(loginState).setUsername(null);
        verify(loginState).setPasswordError(null);
        verify(loginState).setLoggedIn(false);
        verify(loginViewModel).firePropertyChanged();

        verify(viewManagerModel).setActiveView(loginViewModel.getViewName());
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPrepareUserPurchaseHistoryView() {
        DashboardOutputData dashboardOutputData = mock(DashboardOutputData.class);
        when(dashboardOutputData.getPortfolio()).thenReturn(mock(Portfolio.class));

        UserPurchaseHistoryState userPurchaseHistoryState = mock(UserPurchaseHistoryState.class);
        when(userPurchaseHistoryViewModel.getState()).thenReturn(userPurchaseHistoryState);

        dashboardPresenter.prepareUserPurchaseHistoryView(dashboardOutputData);

        verify(userPurchaseHistoryViewModel).setState(userPurchaseHistoryState);
        verify(userPurchaseHistoryState).setPortfolio(dashboardOutputData.getPortfolio());
        verify(userPurchaseHistoryViewModel).firePropertyChanged();
        verify(viewManagerModel).setActiveView(userPurchaseHistoryViewModel.getViewName());
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPrepareBuyView() {
        DashboardOutputData dashboardOutputData = mock(DashboardOutputData.class);
        when(dashboardOutputData.getUsername()).thenReturn("testUser");
        when(dashboardOutputData.getPortfolio()).thenReturn(mock(Portfolio.class));

        BuyState buyState = mock(BuyState.class);
        when(buyViewModel.getState()).thenReturn(buyState);

        dashboardPresenter.prepareBuyView(dashboardOutputData);

        verify(buyViewModel).setState(buyState);
        verify(buyState).setUsername("testUser");
        verify(buyState).setPortfolio(dashboardOutputData.getPortfolio());
        verify(buyViewModel).firePropertyChanged();
        verify(viewManagerModel).setActiveView(buyViewModel.getViewName());
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPrepareStockQuery() {
        dashboardPresenter.prepareStockQuery();

        verify(viewManagerModel).setActiveView(queryStockViewModel.getViewName());
        verify(viewManagerModel).firePropertyChanged();
    }
}
