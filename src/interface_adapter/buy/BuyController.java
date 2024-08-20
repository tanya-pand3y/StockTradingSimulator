package interface_adapter.buy;

import entity.Portfolio;
import use_case.buy.BuyInputBoundary;
import use_case.buy.BuyInputData;

public class BuyController {
    private final BuyInputBoundary interactor;

    /**
     * Creates a buy controller given an interactor
     * @param interactor the interactor
     */
    public BuyController(BuyInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Updates the share price given a ticker
     * @param ticker the ticker
     */
    public void fetchSharePrice(String ticker) {
        double sharePrice = interactor.getSharePrice(ticker);
    }

    /**
     * Executes a buy order
     * @param stockTicker the stock ticker
     * @param quantity the quantity
     * @param portfolio the portfolio
     */
    public void execute(String stockTicker, int quantity, Portfolio portfolio) {
        BuyInputData inputData = new BuyInputData(stockTicker, quantity, portfolio);
        interactor.execute(inputData);
    }

    /**
     * Cancels the buy
     */
    public void cancel() {
        interactor.cancel();
    }
}