package interface_adapter.dashboard;

import entity.Portfolio;
import use_case.dashboard.DashboardInputBoundary;
import use_case.dashboard.DashboardInputData;
import use_case.dashboard.DashboardOutputData;

public class DashboardController {
    private final DashboardInputBoundary dashboardInteractor;
    public DashboardController(DashboardInputBoundary dashboardInteractor) {
        this.dashboardInteractor = dashboardInteractor;
    }

    public Object[][] getUserPortfolioArrays(Portfolio portfolio){
        return this.dashboardInteractor.getUserPortfolioArray(portfolio);
    }

    public Portfolio getUserPortfolio(String username){
        return this.dashboardInteractor.getUserPortfolio(username);
    }

    public void sellButtonClicked(DashboardOutputData dashboardOutputData){
        this.dashboardInteractor.prepareSellView(dashboardOutputData);
    }
}
