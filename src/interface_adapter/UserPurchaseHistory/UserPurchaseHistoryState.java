package interface_adapter.UserPurchaseHistory;

import entity.Portfolio;

public class UserPurchaseHistoryState {
    private Portfolio portfolio = null;


    public UserPurchaseHistoryState() {
    }

    /**
     * Gets the portfolio
     * @return the portfolio
     */
    public Portfolio getPortfolio() {
        return portfolio;
    }

    /**
     * Sets the portfolio
     * @param portfolio the portfolio
     */
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

}
