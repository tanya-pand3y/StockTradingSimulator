package entity;

import data_access.StockQuantityDataAccessInterface;

public class Holding {
    private Stock stock;
    private int quantity;
    private double currentValue;
    private double PnL;
    private StockTransactionHistory stockTransactionHistory;

    public Holding(Stock stock, StockTransactionHistory stockTransactionHistory) {
        this.stock = stock;
        this.stockTransactionHistory = stockTransactionHistory;
        this.quantity = stockTransactionHistory.getTotalQuantity();
        this.currentValue = this.stock.getCurrentPrice()*this.quantity;
        this.PnL = this.currentValue - stockTransactionHistory.getTotalTransactionValue();
    }

    public void recalculate() {
        this.stockTransactionHistory.recalculate();
        this.quantity = stockTransactionHistory.getTotalQuantity();
        this.currentValue = this.stock.getCurrentPrice()*this.quantity;
        this.PnL = this.currentValue - this.stockTransactionHistory.getTotalTransactionValue();

    }

    public void addTransaction(Transaction transaction, String username, StockQuantityDataAccessInterface stockQuantityDao){
        this.stockTransactionHistory.addTransaction(transaction);
        stockQuantityDao.addEntry(username, this.stock.getTicker(), transaction.getDate(), transaction.getPrice(), transaction.getQuantity());
        this.recalculate();
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCurrentValue() {
        this.recalculate();
        return currentValue;
    }
    public double getPnL() {
        this.recalculate();
        return this.PnL;
    }

    public StockTransactionHistory getStockTransactionHistory() {
        return stockTransactionHistory;
    }
}
