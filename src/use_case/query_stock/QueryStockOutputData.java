package use_case.query_stock;

import java.util.ArrayList;

public class QueryStockOutputData {
    private final String ticker;
    private final double currentPrice;
    //private final String startDate;
    //private final String endDate;
    private final ArrayList priceHistory;

    public QueryStockOutputData(String ticker, double currentPrice, ArrayList priceHistory) {
        this.ticker = ticker;
        this.currentPrice = currentPrice;
        this.priceHistory = priceHistory != null ? new ArrayList<>(priceHistory) : null;
        //this.startDate = startDate;
        //this.endDate = endDate;
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

//    public String getStartDate() {
//        return startDate;
//    }
//    public String getEndDate() {
//        return endDate;
//    }
public static void main(String[] args) {
    ArrayList<Double> history = new ArrayList<>();
    history.add(148.0);
    history.add(149.0);
    history.add(150.0);

    QueryStockOutputData dataWithHistory = new QueryStockOutputData("AAPL", 150.0, history);
    QueryStockOutputData dataWithoutHistory = new QueryStockOutputData("GOOG", 2800.0, null);

    System.out.println("Ticker: " + dataWithHistory.getTicker());
    System.out.println("Current Price: " + dataWithHistory.getCurrentPrice());
    System.out.println("Has Price History: " + dataWithHistory.hasPriceHistory());
    System.out.println("Price History: " + dataWithHistory.getPriceHistory());

    System.out.println("Ticker: " + dataWithoutHistory.getTicker());
    System.out.println("Current Price: " + dataWithoutHistory.getCurrentPrice());
    System.out.println("Has Price History: " + dataWithoutHistory.hasPriceHistory());
    System.out.println("Price History: " + dataWithoutHistory.getPriceHistory());
}

}
