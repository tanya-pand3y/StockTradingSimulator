package interface_adapter.buy;

import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;
import use_case.buy.BuyOutputBoundary;
import use_case.buy.BuyOutputData;

/**
 * Presents the buy view
 */
public class BuyPresenter implements BuyOutputBoundary {
    private final BuyViewModel buyViewModel;
    private final DashboardViewModel dashboardViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Creates a buy presenter
     * @param buyViewModel the buy view model
     * @param dashboardViewModel the dashboard view model
     * @param viewManagerModel the view manager model
     */
    public BuyPresenter(BuyViewModel buyViewModel,
                        DashboardViewModel dashboardViewModel,
                        ViewManagerModel viewManagerModel) {
        this.buyViewModel = buyViewModel;
        this.dashboardViewModel = dashboardViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Creates a success view
     * @param response the output data
     */
    public void prepareSuccessView(BuyOutputData response) {
        System.out.println("Returning to dashboard, buy successful");
        dashboardViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(dashboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Cancels
     */
    public void prepareCancelView() {
        System.out.println("Returning to dashboard, buy cancelled");
        viewManagerModel.setActiveView(dashboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Creates a fail view
     * @param errorMessage the error message
     */
    public void prepareFailView(String errorMessage) {
        System.out.println("Epic fail!! " + errorMessage);
        buyViewModel.setErrorMessage(errorMessage);
        // TODO implement!
    }

    /**
     * Sets the share price
     * @param sharePrice the share price
     */
    public void setSharePrice(double sharePrice) {
        buyViewModel.setSharePrice(sharePrice);
    }
}
