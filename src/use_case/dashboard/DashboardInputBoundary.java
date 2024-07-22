package use_case.dashboard;

import entity.Portfolio;

public interface DashboardInputBoundary {
    Object[][] getUserPortfolioArray(String username);
    Portfolio getUserPortfolio(String username);
    void prepareSellView(DashboardOutputData dashboardOutputData);
}
