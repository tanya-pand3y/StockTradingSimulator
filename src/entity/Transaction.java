package entity;

import java.time.ZonedDateTime;

public class Transaction {
    private Double price;
    private int quantity;
    private String date;

    public Transaction(String date, Double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }

    public Double getTotalAmount(){
        return price * quantity;
    }
}
