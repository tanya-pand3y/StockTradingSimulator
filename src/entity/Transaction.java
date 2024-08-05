package entity;

import java.time.ZonedDateTime;

public class Transaction {
    private Double price;
    private int quantity;
    private String date;

    /**
     * Creates a transaction given parameters
     * @param date the transaction date
     * @param price the transaction price
     * @param quantity the transaction quantity
     */
    public Transaction(String date, Double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }

    /**
     * Returns the transaction price
     * @return the transaction price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the quantity
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the date
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the total cash value of the transaction
     * @return the total cash value of the transaction
     */
    public Double getTotalAmount(){
        return price * quantity;
    }
}
