package entity;

public class Holding {
    private Stock stock;
    private double purchasePrice;
    private int quantity;
    private double changeInValue;

    public Holding(Stock stock, double purchasePrice, int quantity) {
        this.stock = stock;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
        this.changeInValue = purchasePrice * quantity;
    }

    private void recalculate() {
        this.changeInValue = stock.getCurrentPrice() * quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public double getCostBasis() {
        return purchasePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getValue() {
        this.recalculate();
        return changeInValue;
    }
}
