package use_case.query_stock;

import data_access.StockCurrentAPIDataAccessObject;
import data_access.StockHistoryAPIDataAccessObject;

import java.util.ArrayList;

public class QueryStockInteractor implements QueryStockInputBoundary {
    private final QueryStockOutputBoundary outputBoundary;

    public QueryStockInteractor(QueryStockOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

//    @Override
//    public void execute(QueryStockInputData inputData) {
//        // Retrieve Stock data
//        String ticker = inputData.getTicker();
//        String startDate = inputData.getStart_date();
//        String endDate = inputData.getEnd_date();
//
//        //If the user enters a start and end date, get the price history
//        if (startDate != "" && endDate != "") {
//            StockHistoryAPIDataAccessObject historyDAO = new StockHistoryAPIDataAccessObject(ticker, startDate, endDate);
//            ArrayList<Double> priceHistory = historyDAO.getClose();
//        }
//        //Get the current stock information
//        StockCurrentAPIDataAccessObject currentDAO = new StockCurrentAPIDataAccessObject(ticker);
//        Double currentPrice = currentDAO.getClose();
//
//        //Get Company name as well
//        //Prepare output data
//        //QueryStockOutputData outputData = new QueryStockOutputData(ticker, currentPrice, startDate, endDate);
//
//
//
//        //outputBoundary.present(outputData);
//    }
public void execute(QueryStockInputData inputData) {
    // Retrieve Stock data
    String ticker = inputData.getTicker();
    String startDate = inputData.getStart_date();
    String endDate = inputData.getEnd_date();
    
    ArrayList<Double> priceHistory = null;

    // Get the current stock information
    StockCurrentAPIDataAccessObject currentDAO = new StockCurrentAPIDataAccessObject(ticker);
    Double currentPrice = currentDAO.getClose();

    // If the user enters a start and end date, get the price history
    if (!startDate.isEmpty() && !endDate.isEmpty()) {
        StockHistoryAPIDataAccessObject historyDAO = new StockHistoryAPIDataAccessObject(ticker, startDate, endDate);
        priceHistory = historyDAO.getClose();
    }

    // Prepare output data
    QueryStockOutputData outputData = new QueryStockOutputData(ticker, currentPrice, startDate, endDate, priceHistory);

    // Present output data
    outputBoundary.present(outputData);
    }
}
