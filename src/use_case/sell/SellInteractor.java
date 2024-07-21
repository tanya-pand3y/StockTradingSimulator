package use_case.sell;

import data_access.StockQuantityDataAccessInterface;
import entity.Stock;
import entity.User;

import java.util.ArrayList;

public class SellInteractor implements SellInputBoundary {
    final SellOutputBoundary sellPresenter;
    final StockQuantityDataAccessInterface stockQuantityDataAccessObject;

    public SellInteractor(SellOutputBoundary sellOutputBoundary,
                          StockQuantityDataAccessInterface stockQuantityDataAccessObject) {
        this.sellPresenter = sellOutputBoundary;
        this.stockQuantityDataAccessObject = stockQuantityDataAccessObject;;
    }

    public ArrayList<String> getHeldStocks(String username) {
        this.stockQuantityDataAccessObject.fetchData(username);
        return this.stockQuantityDataAccessObject.getTicker();
    }

    // TODO Fix the execute class by modifying the DAO to allow for the string inputs to become Users and Stocks.
    public void execute(SellInputData sellInputData) {
//        if (sellInputData.getQuantity() > sellInputData.getQuantityHeld()) {
//            sellPresenter.prepareFailView("Insufficient quantity in account");
//        } else if (sellInputData.getQuantity() == sellInputData.getQuantityHeld()) {
//            for (String ticker: this.stockQuantityDataAccessObject.getTicker()) {
//                if(ticker.equals(sellInputData.getStock().getTicker())) {
//                    int i = this.stockQuantityDataAccessObject.getTicker().indexOf(ticker);
//                    this.stockQuantityDataAccessObject.getTicker().remove(i);
//                    this.stockQuantityDataAccessObject.getQuantities().remove(i);
//                }
//            }
//            SellOutputData sellOutputData = new SellOutputData();
//            sellInputData.getPortfolio().removeHolding(sellInputData.getStock()); // remove holding from portfolio
//
//            sellPresenter.prepareSuccessView(sellOutputData);
//        } else {
//            for (String ticker: this.stockQuantityDataAccessObject.getTicker()) {
//                if(ticker.equals(sellInputData.getStock().getTicker())) {
//                    int i = this.stockQuantityDataAccessObject.getTicker().indexOf(ticker);
//                    int newQuantity = this.stockQuantityDataAccessObject.getQuantities().get(i)
//                                        + sellInputData.getQuantity();
//                    this.stockQuantityDataAccessObject.getQuantities().set(i, newQuantity);
//                }
//            }
//            sellInputData.getPortfolio().getHolding(sellInputData.getStock())
//                        .reduceQuantity(sellInputData.getQuantity());
//            SellOutputData sellOutputData = new SellOutputData();
//            sellPresenter.prepareSuccessView(sellOutputData);
//        }
        stockQuantityDataAccessObject.deleteStocks(sellInputData.getUsername(), sellInputData.getTicker(),
                sellInputData.getQuantity());
        SellOutputData sellOutputData = new SellOutputData();
        sellPresenter.prepareSuccessView(sellOutputData);
    }
}
