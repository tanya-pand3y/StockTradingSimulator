package interface_adapter.dashboard;

import entity.Portfolio;
import use_case.dashboard.DashboardInputBoundary;
import use_case.dashboard.DashboardInputData;

public class DashboardController {
    private final DashboardInputBoundary dashboardInteractor;
    public DashboardController(DashboardInputBoundary dashboardInteractor) {
        this.dashboardInteractor = dashboardInteractor;
    }

    public Object[][] getUserPortfolioArrays(String username){
        return this.dashboardInteractor.getUserPortfolioArray(username);
    }
}
