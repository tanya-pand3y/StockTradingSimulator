package entity;

import data_access.StockQuantityDataAccessInterface;

public class Holding {
    private Stock stock;
    private int quantity;
    private double currentValue;
    private double PnL;
    private StockTransactionHistory stockTransactionHistory;

    /**
     * Creates a holding object given a stock and the transaction history
     * @param stock the stock
     * @param stockTransactionHistory the transaction history
     */
    public Holding(Stock stock, StockTransactionHistory stockTransactionHistory) {
        this.stock = stock;
        this.stockTransactionHistory = stockTransactionHistory;
        this.quantity = stockTransactionHistory.getTotalQuantity();
        this.currentValue = this.stock.getCurrentPrice()*this.quantity;
        this.PnL = this.currentValue - stockTransactionHistory.getTotalTransactionValue();
    }

    /**
     * Recalculates holding values
     */
    public void recalculate() {
        this.stockTransactionHistory.recalculate();
        this.quantity = stockTransactionHistory.getTotalQuantity();
        this.currentValue = this.stock.getCurrentPrice()*this.quantity;
        this.PnL = this.currentValue - this.stockTransactionHistory.getTotalTransactionValue();

    }

    /**
     * Adds a transaction to the holding
     * @param transaction the transaction
     * @param username the username
     * @param stockQuantityDao the data access used
     */
    public void addTransaction(Transaction transaction, String username, StockQuantityDataAccessInterface stockQuantityDao){
        this.stockTransactionHistory.addTransaction(transaction);
        stockQuantityDao.addEntry(username, this.stock.getTicker(), transaction.getDate(), transaction.getPrice(), transaction.getQuantity());
        this.recalculate();
    }

    /**
     * Returns the stock associated with the holding
     * @return the stock associated with the holding
     */
    public Stock getStock() {
        return stock;
    }

    /**
     * Returns the quantity of stock held
     * @return the quantity of stock held
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the current value of the holding
     * @return the current value of the holding
     */
    public double getCurrentValue() {
        this.recalculate();
        return currentValue;
    }

    /**
     * Returns the profit/loss of the holding
     * @return the profit/loss of the holding
     */
    public double getPnL() {
        this.recalculate();
        return this.PnL;
    }

    /**
     * Returns the stock transaction history
     * @return the stock transaction history
     */
    public StockTransactionHistory getStockTransactionHistory() {
        return stockTransactionHistory;
    }
}
