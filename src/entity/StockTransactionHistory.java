package entity;
import java.util.ArrayList;

public class StockTransactionHistory {
    private String ticker;
    private ArrayList<Transaction> transactions;
    private Double totalTransactionValue;
    private int totalQuantity;

    /**
     * Creates a StockTransactionHistory object
     */
    public StockTransactionHistory() {
        this.ticker = " ";
        this.transactions = new ArrayList<>();
        this.totalQuantity = 0;
        this.totalTransactionValue = 0.0;
    }

    /**
     * Returns the ticker associated with the object
     * @return the ticker
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Sets the ticker
     * @param ticker the ticker
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    /**
     * Returns a list of transactions in the history
     * @return a list of transactions in the history
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Adds a transaction to the history
     * @param transaction the transaction
     */
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        recalculate();
    }

    /**
     * Returns the total value of transactions
     * @return the total value of transactions
     */
    public double getTotalTransactionValue() {
        return totalTransactionValue;
    }

    /**
     * Returns the total transaction quantity
     * @return the total transaction quantity
     */
    public int getTotalQuantity() {
        return totalQuantity;
    }

    /**
     * Recalculates values
     */
    public void recalculate(){
        this.totalTransactionValue = 0.0;
        for (Transaction transaction : transactions) {
            this.totalTransactionValue += transaction.getTotalAmount();
        }
        this.totalQuantity = 0;
        for (Transaction transaction : transactions) {
            this.totalQuantity += transaction.getQuantity();
        }


    }

}
