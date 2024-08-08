package use_case.query_stock;

import static org.junit.jupiter.api.Assertions.*;
public class TestQueryStockOutputBoundary implements QueryStockOutputBoundary {

    private QueryStockOutputData outputData;
    private boolean dashboardPrepared = false;

    @Override
    public void present(QueryStockOutputData outputData) {
        this.outputData = outputData;
    }

    @Override
    public void prepareDashboardView() {
        this.dashboardPrepared = true;
    }

    public QueryStockOutputData getOutputData() {
        return outputData;
    }

    public boolean isDashboardPrepared() {
        return dashboardPrepared;
    }
}
