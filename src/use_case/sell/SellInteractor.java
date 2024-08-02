package use_case.sell;

import data_access.StockQuantityDataAccessInterface;
import entity.Portfolio;
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

    public ArrayList<String> getHeldStocks(Portfolio portfolio) {
        return portfolio.getHeldStocks();
    }

    public void execute(SellInputData sellInputData) {
        System.out.println(sellInputData.getQuantity());
        sellInputData.getPortfolio().deleteStocks(sellInputData.getTicker(), sellInputData.getQuantity());
        SellOutputData sellOutputData = new SellOutputData();
        sellPresenter.prepareSuccessView(sellOutputData);
    }
}
