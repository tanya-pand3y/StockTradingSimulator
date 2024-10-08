package interface_adapter.dashboard;

import entity.Portfolio;
import use_case.dashboard.DashboardInputBoundary;
import use_case.dashboard.DashboardInputData;
import use_case.dashboard.DashboardInteractor;
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

    public void logoutPressed(){
        this.dashboardInteractor.logoutPressed();
    }

    public void transactionHistoryPressed(Portfolio portfolio){
        DashboardOutputData dashboardOutputData = new DashboardOutputData(portfolio.getUsername(), portfolio);
        this.dashboardInteractor.transactionHistoryPressed(dashboardOutputData);
    }

    public void buyButtonClicked(DashboardOutputData dashboardOutputData) {
        this.dashboardInteractor.prepareBuyView(dashboardOutputData);
    }

    public void stockQueryPressed(){
        this.dashboardInteractor.stockQueryPressed();
    }
}
