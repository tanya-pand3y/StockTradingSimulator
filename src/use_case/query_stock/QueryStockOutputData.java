package use_case.query_stock;

import data_access.StockHistoryAPIDataAccessObject;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class QueryStockOutputData {
    private final String ticker;
    private final double currentPrice;
    private final String startDate;
    private final String endDate;
    private final ArrayList<Double> priceHistory;
    private final ArrayList<ZonedDateTime> dates;

    /**
     * Creates output data to be presented to the viewer
     * @param ticker is the stock ticker
     * @param currentPrice the current price of the stock
     * @param startDate the start date
     * @param endDate the end date
     * @param priceHistory the price of the stock from the start to the end date
     * @param dates the dates from the start to the end date
     */

    public QueryStockOutputData(String ticker, double currentPrice, String startDate, String endDate, ArrayList<Double> priceHistory, ArrayList<ZonedDateTime> dates) {
        this.ticker = ticker;
        this.currentPrice = currentPrice;
        this.priceHistory = priceHistory != null ? new ArrayList<Double>(priceHistory) : null;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dates = dates;
    }

    /**
     * returns the ticker
     * @return the ticker
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * returns the current price of the stock
     * @return the current price of the stock
     */
    public double getCurrentPrice() {
        return currentPrice;
    }

    /**
     * returns the price history
     * @return the price history
     */
    public ArrayList<Double> getPriceHistory() {
        return priceHistory;
    }

    /**
     * returns whether the price history is null or not
     * @returns whether the price history is null or not
     */
    public boolean hasPriceHistory() {
        return priceHistory != null;
    }

    /**
     * returns the start date
     * @return the start date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * returns the end date
     * @return the end date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * returns the dates
     * @return the dates
     */
    public ArrayList<ZonedDateTime> getDates() {
        return dates;
    }
}
