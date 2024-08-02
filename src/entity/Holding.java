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
        this.changeInValue = (this.stock.getCurrentPrice() - purchasePrice) * quantity;
    }

    private void recalculate() {
        this.changeInValue = (stock.getCurrentPrice() - this.purchasePrice) * quantity;
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

    public double getChangeInValueValue() {
        this.recalculate();
        return this.changeInValue;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.recalculate();
    }

    public void reduceQuantity(int quantity) {
        this.quantity -= quantity;
        this.recalculate();
    }
}
