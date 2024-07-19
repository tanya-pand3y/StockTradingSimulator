package interface_adapter.sell;

import entity.Stock;

import java.util.List;

public class SellState {
    private List<Stock> availableStocks;
    private String selectedStockSymbol;
    private int quantity;
    private String message;

    public List<Stock> getAvailableStocks() {
        return availableStocks;
    }

    public void setAvailableStocks(List<Stock> availableStocks) {
        this.availableStocks = availableStocks;
    }

    public String getSelectedStockSymbol() {
        return selectedStockSymbol;
    }

    public void setSelectedStockSymbol(String selectedStockSymbol) {
        this.selectedStockSymbol = selectedStockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}