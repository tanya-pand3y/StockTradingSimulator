package entity;

import java.time.ZonedDateTime;

public class UserPurchaseHistory {

    private String ticker;     // Stock ticker symbol
    private double price;      // Price at which the stock was purchased
    private int quantity;      // Number of shares purchased
    private ZonedDateTime date; // Date and time of the purchase

    /**
     * Creates a UserPurchaseHistory object
     * @param ticker the ticker
     * @param price the price
     * @param quantity the quantity
     * @param date the date
     */
    public UserPurchaseHistory(String ticker, double price, int quantity, ZonedDateTime date) {
        this.ticker = ticker;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }

    /**
     * Returns the stock ticker
     * @return the stock ticker
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Returns the purchase price
     * @return the purchase price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the quantity of the transaction
     * @return the quantity of the transaction
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * returns the date and time of the transaction
     * @return the date and time of the transaction
     */
    public ZonedDateTime getDate() {
        return date;
    }

    /**
     * Returns the string associated with this purchase history
     * @return the string associated with this purchase history
     */
    @Override
    public String toString() {
        return "PurchaseHistory{" +
                "ticker='" + ticker + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", date=" + date +
                '}';
    }
}
