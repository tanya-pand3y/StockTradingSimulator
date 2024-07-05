package src.entity;

public class Stock {
    private String ticker;
    private double currentPrice;

    public Stock(String ticker) {
        this.ticker = ticker;
        // TODO initialize price with API call
    }

    private void checkPrice() {
        // TODO implement method with API call
    }
    public double getCurrentPrice() {
        return currentPrice;
    }
}
