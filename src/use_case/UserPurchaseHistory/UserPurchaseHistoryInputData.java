package use_case.UserPurchaseHistory;

import entity.Portfolio;

public class UserPurchaseHistoryInputData {
    private final String ticker;
    private final Portfolio portfolio;

    public UserPurchaseHistoryInputData(String ticker, Portfolio portfolio) {
        this.ticker = ticker;
        this.portfolio = portfolio;
    }

    public String getTicker() {
        return ticker;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

}
