package use_case.buy;

public class BuyInputData {
    private final String ticker;
    private final int quantity;
    private final String username;

    public BuyInputData(String ticker, int quantity, String username) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.username = username;
    }

    public String getTicker() {
        return ticker;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUsername() {
        return username;
    }
}
