package use_case.buy;

import data_access.StockQuantityDataAccessInterface;
import data_access.TiingoAPIDataAccessInterface;

public class BuyInteractor implements BuyInputBoundary {
    final BuyOutputBoundary buyPresenter;
    final StockQuantityDataAccessInterface stockQuantityDataAccessObject;

    public BuyInteractor(BuyOutputBoundary buyOutputBoundary,
                         StockQuantityDataAccessInterface stockQuantityDataAccessObject) {
        this.buyPresenter = buyOutputBoundary;
        this.stockQuantityDataAccessObject = stockQuantityDataAccessObject;
    }

    public void execute(BuyInputData buyInputData) {
        String result = buyInputData.getPortfolio().buyStock(buyInputData.getTicker(), buyInputData.getQuantity());
        if (result == "") {
            BuyOutputData buyOutputData = new BuyOutputData();
            buyPresenter.prepareSuccessView(buyOutputData);
        } else {
            buyPresenter.prepareFailView(result);
        }
    }

    public void cancel() {
        buyPresenter.prepareCancelView();
    }
}
