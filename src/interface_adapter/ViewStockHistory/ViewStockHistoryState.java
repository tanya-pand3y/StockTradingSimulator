package interface_adapter.ViewStockHistory;

import java.util.ArrayList;
import java.util.List;

public class ViewStockHistoryState {
    private String ticker;
    private String startDate;
    private String endDate;
    private ArrayList<Double> stockPrices; // Represents historical stock prices
    private String errorMessage;
    private boolean isLoading; // Indicates whether the data is currently being loaded

    public ViewStockHistoryState() {
        this.ticker = "";
        this.startDate = "";
        this.endDate = "";
        this.stockPrices = null;
        this.errorMessage = "";
        this.isLoading = false;
    }

    // Getters and Setters
    public String getTicker() {
        return ticker;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public ArrayList<Double> getStockPrices() {
        return stockPrices;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public boolean isLoading() {
        return isLoading;
    }
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public void setStockPrices(ArrayList<Double> stockPrices) {
        this.stockPrices = stockPrices;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }
}

