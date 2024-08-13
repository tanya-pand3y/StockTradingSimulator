package use_case.query_stock;

public class QueryStockInputData {

    final private String ticker;
    final String start_date;
    final String end_date;

    /**
     * Creates a package of SellInputData, when only the stock ticker is given by the user
     * @param ticker is the stock ticker
     */

    public QueryStockInputData(String ticker) {
        this.ticker = ticker;
        this.start_date = "";
        this.end_date = "";
    }

    /**
     * Creates a package of SellInputData, when user gives start and end dates as well
     * @param ticker is the stock ticker
     * @param start_date is the start date
     * @param end_date is the end date
     */

    public QueryStockInputData(String ticker, String start_date, String end_date) {
        this.ticker = ticker;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    /**
     * getters that return the ticker, start date and end date
     * @return the ticker
     * @return the start date
     * @return the end date
     */
    String getTicker() {return this.ticker;}
    String getStart_date() {return this.start_date;}
    String getEnd_date() {return this.end_date;}
}
