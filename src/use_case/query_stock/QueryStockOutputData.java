package use_case.query_stock;

import data_access.StockHistoryAPIDataAccessObject;

import java.util.ArrayList;

public class QueryStockOutputData {
    private final String ticker;
    private final double currentPrice;
    private final String startDate;
    private final String endDate;
    private final ArrayList priceHistory;

    public QueryStockOutputData(String ticker, double currentPrice, String startDate, String endDate, ArrayList priceHistory) {
        this.ticker = ticker;
        this.currentPrice = currentPrice;
        this.priceHistory = priceHistory != null ? new ArrayList<>(priceHistory) : null;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public String getTicker() {
        return ticker;
    }
    public double getCurrentPrice() {
        return currentPrice;
    }
    public ArrayList getPriceHistory() {
        return priceHistory;
    }
    public boolean hasPriceHistory() {
        return priceHistory != null;
    }

    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }

}
