package interface_adapter.buy;

import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;
import use_case.buy.BuyOutputBoundary;
import use_case.buy.BuyOutputData;

public class BuyPresenter implements BuyOutputBoundary {
    private final BuyViewModel buyViewModel;
    private final DashboardViewModel dashboardViewModel;
    private ViewManagerModel viewManagerModel;

    public BuyPresenter(BuyViewModel buyViewModel,
                        DashboardViewModel dashboardViewModel,
                        ViewManagerModel viewManagerModel) {
        this.buyViewModel = buyViewModel;
        this.dashboardViewModel = dashboardViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(BuyOutputData response) {
        System.out.println("Returning to dashboard, buy successful");
        dashboardViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(dashboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareCancelView() {
        System.out.println("Returning to dashboard, buy cancelled");
        viewManagerModel.setActiveView(dashboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String errorMessage) {
        System.out.println("Epic fail!! " + errorMessage);
        buyViewModel.setErrorMessage(errorMessage);
        // TODO implement!
    }

    public void setSharePrice(double sharePrice) {
        buyViewModel.setSharePrice(sharePrice);
    }
}
