package use_case.query_stock;

public interface QueryStockOutputBoundary {

    /**
     * Prepares the view given output data
     * @param outputData the output data
     */
    void present(QueryStockOutputData outputData);

    /**
     * Prepares the dashboard view given output data
     */
    void prepareDashboardView();
}
