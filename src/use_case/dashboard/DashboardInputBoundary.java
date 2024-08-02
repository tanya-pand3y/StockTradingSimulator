package use_case.dashboard;

import entity.Portfolio;

public interface DashboardInputBoundary {
    Object[][] getUserPortfolioArray(Portfolio portfolio);
    Portfolio getUserPortfolio(String username);
    void prepareSellView(DashboardOutputData dashboardOutputData);
}
