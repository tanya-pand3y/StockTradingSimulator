package src.entity;

public class Stock {
    private String name;
    private String ticker;
    private double currentPrice;
    private int volume;

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

    public String getTicker() {
        return ticker;
    }
}
