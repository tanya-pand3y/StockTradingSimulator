package entity;

public class Transaction {
    private Stock stock;
    private int quantity;
    private boolean isBuy;
    private CommonUser user;
    private double transactionPrice;
    // TODO add timestamp / id

    public Transaction(CommonUser user, Stock stock, int quantity, boolean isBuy) {
        this.stock = stock;
        this.quantity = quantity;
        this.isBuy = isBuy;
        this.user = user;
        this.transactionPrice = stock.getCurrentPrice();
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
        if (isBuy) {
            // TODO (MH) Check with hasCash method for sufficient funds
            user.getPortfolio().deductCash(transactionPrice * quantity);
            user.getPortfolio().addHolding(new Holding(stock, transactionPrice, quantity));
            System.out.println(quantity + " shares of " + stock.getTicker() + " bought at " + transactionPrice);
        }
        else {
            // TODO (MH) selling
        }
    }

    /**
     * Checks for sufficient shares in a user's portfolio to execute a sale
     * @param stock the stock
     * @param user the user
     * @param quantity the quantity
     * @return Whether the shares are present
     */
    private boolean hasShares(Stock stock, User user, int quantity) {
        //return user.getPortfolio().getHoldings().findHoldingQuantity(stock) >= quantity;
        // TODO fix
        return false;
    }

    private boolean hasCash(User user, int quantity, double price) {
        return user.getPortfolio().getCash() >= quantity * price;
    }
}
