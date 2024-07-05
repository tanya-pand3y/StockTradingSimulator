package src.entity;

public class Holding {
    private Stock stock;
    private double costBasis;
    private int quantity;
    private double value;

    public Holding(Stock stock, double costBasis, int quantity) {
        this.stock = stock;
        this.costBasis = costBasis;
        this.quantity = quantity;
        this.value = costBasis * quantity;
    }

    private void recalculate() {
        this.value = stock.getCurrentPrice() * quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public double getCostBasis() {
        return costBasis;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getValue() {
        this.recalculate();
        return value;
    }
}
