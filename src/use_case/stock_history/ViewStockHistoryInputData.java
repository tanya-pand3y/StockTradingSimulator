package use_case.stock_history;

public class ViewStockHistoryInputData {
    private final String ticker;
    private final String startDate;
    private final String endDate;

    public ViewStockHistoryInputData(String ticker, String startDate, String endDate) {
        this.ticker = ticker;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTicker() {
        return ticker;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
