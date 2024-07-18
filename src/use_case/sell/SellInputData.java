package use_case.sell;

import entity.Portfolio;
import entity.Stock;
import entity.User;

public class SellInputData {
    private final Stock stock;
    private final int quantity;
    private final double price;
    private final User user;

    /**
     * Creates a package of SellInputData
     * @param stock the stock
     * @param quantity the quantity
     * @param price the price
     */
    public SellInputData(Stock stock, int quantity, double price, User user) {
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
        this.user = user;
    }

    /**
     * Returns the stock
     * @return the stock
     */
    public Stock getStock() {
        return stock;
    }

    /**
     * Returns the quantity
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the price
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    public Portfolio getPortfolio() {
        return user.getPortfolio();
    }

    /**
     * Returns the quantity of the stock held in the portfolio of the user
     * @return the quantity of the stock held in the portfolio of the user
     */
    public int getQuantityHeld() {
        return user.getPortfolio().getQuantityByStock(stock);
    }
}
