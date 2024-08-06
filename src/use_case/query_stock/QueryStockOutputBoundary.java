package use_case.query_stock;

public interface QueryStockOutputBoundary {
    void present(QueryStockOutputData outputData);
    void prepareDashboardView();
}
