package interface_adapter.dashboard;

import interface_adapter.ViewManagerModel;
import interface_adapter.sell.SellState;
import interface_adapter.sell.SellViewModel;
import use_case.dashboard.DashboardOutputBoundary;
import use_case.dashboard.DashboardOutputData;

public class DashboardPresenter implements DashboardOutputBoundary {

    private final DashboardViewModel dashboardViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SellViewModel sellViewModel;

    public DashboardPresenter(ViewManagerModel viewManagerModel, SellViewModel sellViewModel, DashboardViewModel dashboardViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.sellViewModel = sellViewModel;
        this.dashboardViewModel = dashboardViewModel;
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
}
