package entity;

import java.time.ZonedDateTime;

public class UserPurchaseHistory {

    private String ticker;     // Stock ticker symbol
    private double price;      // Price at which the stock was purchased
    private int quantity;      // Number of shares purchased
    private ZonedDateTime date; // Date and time of the purchase

    // Constructor to initialize all fields
    public void PurchaseHistory(String ticker, double price, int quantity, ZonedDateTime date) {
        this.ticker = ticker;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }

    public UserPurchaseHistory(String ticker, double price, int quantity, ZonedDateTime date) {
        this.ticker = ticker;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }

    // Getter for the stock ticker symbol
    public String getTicker() {
        return ticker;
    }

    // Getter for the purchase price
    public double getPrice() {
        return price;
    }

    // Getter for the quantity of shares purchased
    public int getQuantity() {
        return quantity;
    }

    // Getter for the date and time of the purchase
    public ZonedDateTime getDate() {
        return date;
    }

    // Override toString to provide a string representation of the PurchaseHistory
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
