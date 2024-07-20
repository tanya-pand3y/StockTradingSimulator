package use_case.stock_history;

public interface ViewStockHistoryInputBoundary {
    /**
     * Executes the request to view stock history.
     * @param inputData the input data required to view stock history.
     */
    void execute(ViewStockHistoryInputData inputData);
}
