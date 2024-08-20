package interface_adapter.QueryStock;

import use_case.query_stock.QueryStockInputBoundary;
import use_case.query_stock.QueryStockInputData;
import use_case.query_stock.QueryStockOutputBoundary;

public class QueryStockController {
    private final QueryStockInputBoundary interactor;

    /**
     * Creates a query stock controller
     * @param interactor the interactor
     */
    public QueryStockController(QueryStockInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes a stock query
     * @param ticker the ticker
     * @param startDate the start date
     * @param endDate the end date
     */
    public void execute(String ticker, String startDate, String endDate){
        QueryStockInputData inputData = new QueryStockInputData(ticker, startDate, endDate);
        interactor.execute(inputData);
    }

    public void backButtonPressed(){
        this.interactor.backButtonPressed();
    }
}
