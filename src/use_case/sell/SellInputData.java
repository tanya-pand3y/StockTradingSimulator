package use_case.sell;

import entity.Portfolio;
import entity.Stock;
import entity.User;

public class SellInputData {
    private final String ticker;
    private final int quantity;
    private final Portfolio portfolio;

    /**
     * Creates a package of SellInputData
     * @param ticker the stock ticker
     * @param quantity the quantity
     * @param portfolio the username
     */
    public SellInputData(String ticker, int quantity, Portfolio portfolio) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.portfolio = portfolio;
    }

    /**
     * Returns the stock ticker
     * @return the stock ticker
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Returns the quantity
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the portfolio
     * @return the portfolio
     */
    public Portfolio getPortfolio() {
        return this.portfolio;
    }

//    public Portfolio getPortfolio() {
//        return user.getPortfolio();
//    }
//
//    /**
//     * Returns the quantity of the stock held in the portfolio of the user
//     * @return the quantity of the stock held in the portfolio of the user
//     */
//    public int getQuantityHeld() {
//        return user.getPortfolio().getQuantityByStock(stock);
//    }
}
