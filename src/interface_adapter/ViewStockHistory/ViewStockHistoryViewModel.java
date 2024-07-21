package interface_adapter.ViewStockHistory;

import java.util.ArrayList;
import java.util.List;

public class ViewStockHistoryViewModel {
    private final String ticker;
    private final List<Double> stockPrices;

    public ViewStockHistoryViewModel(String ticker, List<Double> stockPrices) {
        this.ticker = ticker;
        this.stockPrices = stockPrices;
    }

    public String getTicker() {
        return ticker;
    }

    public List<Double> getStockPrices() {
        return stockPrices;
    }
}