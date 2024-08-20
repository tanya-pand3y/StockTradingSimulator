package interface_adapter.UserPurchaseHistory;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.dashboard.DashboardViewModel;
import use_case.UserPurchaseHistory.UserPurchaseHistoryOutputBoundary;
import use_case.UserPurchaseHistory.UserPurchaseHistoryOutputData;

public class UserPurchaseHistoryPresenter implements UserPurchaseHistoryOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private UserPurchaseHistoryViewModel userPurchaseHistoryViewModel;
    private DashboardViewModel dashboardViewModel;
    public UserPurchaseHistoryPresenter(ViewManagerModel viewManagerModel, UserPurchaseHistoryViewModel userPurchaseHistoryViewModel, DashboardViewModel dashboardViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.userPurchaseHistoryViewModel = userPurchaseHistoryViewModel;
        this.dashboardViewModel = dashboardViewModel;
    }

    /**
     * Prepares the dashboard view
     */
    @Override
    public void prepareDashboardView() {
        viewManagerModel.setActiveView(dashboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
