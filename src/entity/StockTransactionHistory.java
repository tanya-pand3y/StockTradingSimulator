package entity;
import java.util.ArrayList;

public class StockTransactionHistory {
    private String ticker;
    private ArrayList<Transaction> transactions;
    private Double totalTransactionValue;
    private int totalQuantity;


    public StockTransactionHistory() {
        this.ticker = " ";
        this.transactions = new ArrayList<>();
        this.totalQuantity = 0;
        this.totalTransactionValue = 0.0;
    }

    public String getTicker() {
        return ticker;
    }
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        recalculate();
    }
    public double getTotalTransactionValue() {
        return totalTransactionValue;
    }


    public int getTotalQuantity() {
        return totalQuantity;
    }


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
