package interface_adapter.sell;

import entity.Portfolio;

public class SellState {
    private String username;
    private int quantity;
    private Portfolio portfolio;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
    public Portfolio getPortfolio() {
        return portfolio;
    }
}