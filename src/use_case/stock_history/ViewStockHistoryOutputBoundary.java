package use_case.stock_history;

public interface ViewStockHistoryOutputBoundary {
    /**
     * Presents the result of viewing stock history.
     * @param outputData the output data of the stock history view.
     */
    void present(ViewStockHistoryOutputData outputData);
}
