package use_case.stock_history;

import java.util.List;

public class ViewStockHistoryOutputData {
    private final String ticker;
    private final List<Double> stockPrices;

    public ViewStockHistoryOutputData(String ticker, List<Double> stockPrices) {
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
