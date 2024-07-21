package interface_adapter.ViewStockHistory;


import java.util.ArrayList;
import java.util.List;

public class ViewStockHistoryState {
    private String ticker;
    private List<Double> stockPrices;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public List<Double> getStockPrices() {
        return stockPrices;
    }

    public void setStockPrices(List<Double> stockPrices) {
        this.stockPrices = stockPrices;
    }
}