package use_case.query_stock;

public class QueryStockInputData {

    final private String ticker;
    final String start_date;
    final String end_date;

    public QueryStockInputData(String ticker) {
        this.ticker = ticker;
        this.start_date = null;
        this.end_date = null;
    }

    public QueryStockInputData(String ticker, String start_date, String end_date) {
        this.ticker = ticker;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    String getTicker() {return this.ticker;}
    String getStart_date() {return this.start_date;}
    String getEnd_date() {return this.end_date;}
}
