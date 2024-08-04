package use_case.UserPurchaseHistory;

import entity.Portfolio;

public class UserPurchaseHistoryInputData {
    private final String userId;
    private final String ticker;
    private final Portfolio portfolio;

    public UserPurchaseHistoryInputData(String userId, String ticker, Portfolio portfolio) {
        this.userId = userId;
        this.ticker = ticker;
        this.portfolio = portfolio;
    }

    public String getUserId() {
        return userId;
    }

    public String getTicker() {
        return ticker;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

}
