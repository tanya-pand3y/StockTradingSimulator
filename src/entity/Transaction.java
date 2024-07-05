package src.entity;

public class Transaction {
    private Stock stock;
    private int quantity;
    private boolean isBuy;
    private User user;
    // TODO add timestamp / id

    public Transaction(User user, Stock stock, int quantity, boolean isBuy) {
        this.stock = stock;
        this.quantity = quantity;
        this.isBuy = isBuy;
        this.user = user;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void execute() {
        // TODO (SH) add logging feature
        if (isBuy) user.getPortfolio().addHolding(new Holding(stock, stock.getCurrentPrice(), quantity));
        // TODO (MH) selling
    }
}
