package interface_adapter.dashboard;

import entity.Portfolio;
import use_case.dashboard.DashboardInputBoundary;
import use_case.dashboard.DashboardInputData;

public class DashboardController {
    private final DashboardInputBoundary dashboardInteractor;
    public DashboardController(DashboardInputBoundary dashboardInteractor) {
        this.dashboardInteractor = dashboardInteractor;
    }

    public Portfolio getUserPortfolio(String username){
        DashboardInputData usernameInputData = new DashboardInputData(username);
        return this.dashboardInteractor.getUserPorfolio(usernameInputData);
    }
}
