package interface_adapter.QueryStock;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class QueryStockViewModel extends ViewModel {
    private String ticker;
    private Double currentPrice;
    private String startDate;
    private String endDate;
    private ArrayList priceHistory;

    private final PropertyChangeSupport support;

    public QueryStockViewModel(String viewName) {
        super(viewName);
        support = new PropertyChangeSupport(this);
    }

    public String getTicker() {
        return ticker;
    }
    public Double getCurrentPrice() {
        return currentPrice;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }

    // Setters for properties
    public void setTicker(String ticker) {
        String oldTicker = this.ticker;
        this.ticker = ticker;
        support.firePropertyChange("ticker", oldTicker, ticker);
    }

    public void setCurrentPrice(Double currentPrice) {
        Double oldPrice = this.currentPrice;
        this.currentPrice = currentPrice;
        support.firePropertyChange("currentPrice", oldPrice, currentPrice);
    }

    public void setStartDate(String startDate) {
        String oldStartDate = this.startDate;
        this.startDate = startDate;
        support.firePropertyChange("startDate", oldStartDate, startDate);
    }

    public void setEndDate(String endDate) {
        String oldEndDate = this.endDate;
        this.endDate = endDate;
        support.firePropertyChange("endDate", oldEndDate, endDate);
    }

    public ArrayList getPriceHistory() {
        return priceHistory;
    }
    public void setPriceHistory(ArrayList priceHistory) {
        ArrayList oldPriceHistory = this.priceHistory;
        this.priceHistory = priceHistory;
        support.firePropertyChange("priceHistory", oldPriceHistory, priceHistory);
    }

    // Method to update the view with new data
    public void updateView(String ticker, Double currentPrice, String startDate, String endDate, ArrayList priceHistory) {
        setTicker(ticker);
        setCurrentPrice(currentPrice);
        setStartDate(startDate);
        setEndDate(endDate);
        setPriceHistory(priceHistory);
    }

    // Implement abstract methods
    @Override
    public void firePropertyChanged() {
        // This method is not strictly necessary if you use property change listeners directly
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}

