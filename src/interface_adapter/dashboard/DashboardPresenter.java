package interface_adapter.dashboard;

import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryState;
import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.buy.BuyState;
import interface_adapter.buy.BuyViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.sell.SellState;
import interface_adapter.sell.SellViewModel;
import use_case.dashboard.DashboardOutputBoundary;
import use_case.dashboard.DashboardOutputData;
import view.BuyView;

public class DashboardPresenter implements DashboardOutputBoundary {

    private final DashboardViewModel dashboardViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SellViewModel sellViewModel;
    private final LoginViewModel loginViewModel;
    private final UserPurchaseHistoryViewModel userPurchaseHistoryViewModel;
    private final BuyViewModel buyViewModel;

    public DashboardPresenter(ViewManagerModel viewManagerModel,
                              UserPurchaseHistoryViewModel userPurchaseHistoryViewModel,
                              SellViewModel sellViewModel,
                              LoginViewModel loginViewModel,
                              DashboardViewModel dashboardViewModel,
                              BuyViewModel buyViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.sellViewModel = sellViewModel;
        this.dashboardViewModel = dashboardViewModel;
        this.loginViewModel = loginViewModel;
        this.userPurchaseHistoryViewModel = userPurchaseHistoryViewModel;
        this.buyViewModel = buyViewModel;
    }
    @Override
    public void prepareSellView(DashboardOutputData dashboardOutputData) {
        System.out.println("Preparing sell view");
        SellState sellState = this.sellViewModel.getState();
        sellState.setUsername(dashboardOutputData.getUsername());
        sellState.setPortfolio(dashboardOutputData.getPortfolio());
        this.sellViewModel.setState(sellState);
        this.sellViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(sellViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareLogout() {
        DashboardState dashboardState = this.dashboardViewModel.getState();
        dashboardState.setUsername("");
        dashboardState.setPortfolio(null);
        dashboardViewModel.setState(dashboardState);
        dashboardViewModel.firePropertyChanged("UserLogout");

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername("");
        loginState.setPassword("");
        loginState.setLoggedIn(false);
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    public void prepareUserPurchaseHistoryView(DashboardOutputData dashboardOutputData) {
        System.out.println("Preparing user purchase history view");
        UserPurchaseHistoryState userPurchaseHistoryState = this.userPurchaseHistoryViewModel.getState();
        userPurchaseHistoryState.setPortfolio(dashboardOutputData.getPortfolio());
        this.userPurchaseHistoryViewModel.setState(userPurchaseHistoryState);
        this.userPurchaseHistoryViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(userPurchaseHistoryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


    public void prepareBuyView(DashboardOutputData dashboardOutputData) {
        System.out.println("Preparing buy view");
        BuyState buyState = this.buyViewModel.getState();
        buyState.setUsername(dashboardOutputData.getUsername());
        buyState.setPortfolio(dashboardOutputData.getPortfolio());
        this.buyViewModel.setState(buyState);
        this.buyViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(buyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
