package interface_adapter.UserPurchaseHistory;

import entity.Portfolio;

public class UserPurchaseHistoryState {
    private Portfolio portfolio = null;

    public UserPurchaseHistoryState() {
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

}
