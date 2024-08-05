package use_case.buy;

import data_access.StockQuantityDataAccessInterface;

public class BuyInteractor implements BuyInputBoundary {
    final BuyOutputBoundary buyPresenter;
    final StockQuantityDataAccessInterface stockQuantityDataAccessObject;

    public BuyInteractor(BuyOutputBoundary buyOutputBoundary,
                         StockQuantityDataAccessInterface stockQuantityDataAccessObject) {
        this.buyPresenter = buyOutputBoundary;
        this.stockQuantityDataAccessObject = stockQuantityDataAccessObject;
    }

    public void execute(BuyInputData buyInputData) {
        // stockQuantityDataAccessObject.addStocks(buyInputData.getUsername(),
                // buyInputData.getTicker(), buyInputData.getQuantity());
        buyInputData.getPortfolio().buyStock(buyInputData.getTicker(), buyInputData.getQuantity());
        BuyOutputData buyOutputData = new BuyOutputData();
        buyPresenter.prepareSuccessView(buyOutputData);
    }
}
