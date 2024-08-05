package use_case.buy;

import entity.Portfolio;

public class BuyInputData {
    private final String ticker;
    private final int quantity;
    private final Portfolio portfolio;

    public BuyInputData(String ticker, int quantity, Portfolio portfolio) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.portfolio = portfolio;
    }

    public String getTicker() {
        return ticker;
    }

    public int getQuantity() {
        return quantity;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}
