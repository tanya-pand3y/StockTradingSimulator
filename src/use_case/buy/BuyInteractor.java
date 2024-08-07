package use_case.buy;

import data_access.StockCurrentAPIDataAccessObject;

public class BuyInteractor implements BuyInputBoundary {
    final BuyOutputBoundary buyPresenter;
    public BuyInteractor(BuyOutputBoundary buyOutputBoundary) {
        this.buyPresenter = buyOutputBoundary;
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

    @Override
    public double getSharePrice(String ticker) {
        StockCurrentAPIDataAccessObject tiingoAPI = new StockCurrentAPIDataAccessObject(ticker);
        double price = tiingoAPI.getClose();
        buyPresenter.setSharePrice(price);
        return price;
    }
}
