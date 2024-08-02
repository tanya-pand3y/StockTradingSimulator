package use_case.dashboard;

import entity.Portfolio;

public class DashboardOutputData {
    private final String username;
    private final Portfolio portfolio;

    public DashboardOutputData(String username, Portfolio portfolio) {
        this.username = username;
        this.portfolio = portfolio;
    }

    public String getUsername() {
        return username;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}
