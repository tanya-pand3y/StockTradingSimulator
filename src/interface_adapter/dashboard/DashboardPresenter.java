package interface_adapter.dashboard;

import interface_adapter.ViewManagerModel;
import interface_adapter.buy.BuyState;
import interface_adapter.buy.BuyViewModel;
import interface_adapter.sell.SellState;
import interface_adapter.sell.SellViewModel;
import use_case.dashboard.DashboardOutputBoundary;
import use_case.dashboard.DashboardOutputData;

public class DashboardPresenter implements DashboardOutputBoundary {

    private final DashboardViewModel dashboardViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SellViewModel sellViewModel;
    private final BuyViewModel buyViewModel;

    public DashboardPresenter(ViewManagerModel viewManagerModel,
                              SellViewModel sellViewModel,
                              DashboardViewModel dashboardViewModel,
                              BuyViewModel buyViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.sellViewModel = sellViewModel;
        this.dashboardViewModel = dashboardViewModel;
        this.buyViewModel = buyViewModel;
    }
    @Override
    public void prepareSellView(DashboardOutputData dashboardOutputData) {
        System.out.println("Preparing sell view");
        SellState sellState = this.sellViewModel.getState();
        sellState.setUsername(dashboardOutputData.getUsername());
        this.sellViewModel.setState(sellState);
        this.sellViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(sellViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareBuyView(DashboardOutputData dashboardOutputData) {
        System.out.println("Preparing buy view");
        BuyState buyState = this.buyViewModel.getState();
        buyState.setUsername(dashboardOutputData.getUsername());
        this.buyViewModel.setState(buyState);
        this.buyViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(buyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
