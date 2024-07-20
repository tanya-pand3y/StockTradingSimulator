package use_case.stock_history;

import data_access.StockHistoryAPIDataAccessObject;

import java.util.ArrayList;

public class ViewStockHistoryInteractor implements ViewStockHistoryInputBoundary{
//
//    private final StockHistoryAPIDataAccessObject StockHistory;
//    private final ViewStockHistoryOutputBoundary outputBoundary;
//
//    public ViewStockHistoryInteractor(StockHistoryAPIDataAccessObject stockHistory, ViewStockHistoryOutputBoundary outputBoundary) {
//        StockHistory = stockHistory;
//        this.outputBoundary = outputBoundary;

    //(String ticker, String startDate, String endDate)

    @Override
    public void execute(ViewStockHistoryInputData inputData) {
        // Retrieve historical price data from the stock service
        String ticker = inputData.getTicker();
        String startDate = inputData.getStartDate();
        String endDate = inputData.getEndDate();
        StockHistoryAPIDataAccessObject DAO = new StockHistoryAPIDataAccessObject(ticker, startDate, endDate);
        ArrayList<Double> priceHistory = DAO.getClose(); //API call to get history

//        // Prepare output data
//        ViewStockHistoryOutputData outputData = new ViewStockHistoryOutputData(ticker, priceHistory);
//
//        // Send results to the output boundary
//        outputBoundary.present(outputData);
    }
}
