package interface_adapter.QueryStock;

import use_case.query_stock.QueryStockInputBoundary;
import use_case.query_stock.QueryStockInputData;
import use_case.query_stock.QueryStockOutputBoundary;

public class QueryStockController {
    private final QueryStockInputBoundary interactor;

    public QueryStockController(QueryStockInputBoundary interactor) {
        this.interactor = interactor;
    }
    public void execute(String ticker, String startDate, String endDate){
        // Debugging: Print the input values
//        System.out.println("Controller - Ticker: " + ticker);
//        System.out.println("Controller - Start Date: " + startDate);
//        System.out.println("Controller - End Date: " + endDate);
        QueryStockInputData inputData = new QueryStockInputData(ticker, startDate, endDate);
        interactor.execute(inputData);
    }
}
