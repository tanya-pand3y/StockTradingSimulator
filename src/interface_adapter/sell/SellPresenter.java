package interface_adapter.sell;

import interface_adapter.ViewManagerModel;
import use_case.sell.SellOutputBoundary;
import use_case.sell.SellOutputData;
import interface_adapter.dashboard.DashboardViewModel;

public class SellPresenter implements SellOutputBoundary {
    private final SellViewModel sellViewModel;
    private final DashboardViewModel dashboardViewModel;
    private ViewManagerModel viewManagerModel;

    public SellPresenter(DashboardViewModel dashboardViewModel,
                         SellViewModel sellViewModel, ViewManagerModel viewManagerModel) {
        this.sellViewModel = sellViewModel;
        this.viewManagerModel = viewManagerModel;
        this.dashboardViewModel = dashboardViewModel;
    }

    @Override
    public void prepareSuccessView(SellOutputData response) {
        System.out.println("Returning to dashboard, sale successful");
        dashboardViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(dashboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // TODO implement
    }
}
