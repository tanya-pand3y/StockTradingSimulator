package interface_adapter.dashboard;

import entity.Portfolio;

public class DashboardState {
    private String username = "";
    private Portfolio portfolio = null;

    public DashboardState(DashboardState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public DashboardState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}
