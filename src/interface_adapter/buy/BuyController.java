package interface_adapter.buy;

import use_case.buy.BuyInputBoundary;
import use_case.buy.BuyInputData;

public class BuyController {
    private final BuyInputBoundary interactor;

    public BuyController(BuyInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String stockTicker, int quantity, String username) {
        BuyInputData inputData = new BuyInputData(stockTicker, quantity, username);
        interactor.execute(inputData);
    }
}