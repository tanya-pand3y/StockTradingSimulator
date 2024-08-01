package use_case.query_stock;

public interface QueryStockInputBoundary {
    /**
     * Executes the request to view a stock.
     * @param inputData the input data required to view a stock.
     */
    void execute(QueryStockInputData inputData);

}
