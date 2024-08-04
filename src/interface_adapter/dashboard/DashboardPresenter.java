package interface_adapter.dashboard;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.sell.SellState;
import interface_adapter.sell.SellViewModel;
import use_case.dashboard.DashboardOutputBoundary;
import use_case.dashboard.DashboardOutputData;

public class DashboardPresenter implements DashboardOutputBoundary {

    private final DashboardViewModel dashboardViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SellViewModel sellViewModel;
    private final LoginViewModel loginViewModel;

    public DashboardPresenter(ViewManagerModel viewManagerModel, SellViewModel sellViewModel, LoginViewModel loginViewModel, DashboardViewModel dashboardViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.sellViewModel = sellViewModel;
        this.dashboardViewModel = dashboardViewModel;
        this.loginViewModel = loginViewModel;
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
        // todo
        // userpurchhist.viewmod.getstate...
    }

}
