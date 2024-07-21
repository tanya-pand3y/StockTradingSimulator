package use_case.dashboard;

import entity.Portfolio;

public interface DashboardInputBoundary {
    Object[][] getUserPortfolioArray(String username);
    void prepareSellView(DashboardOutputData dashboardOutputData);
}
