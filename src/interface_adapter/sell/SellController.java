package interface_adapter.sell;

import entity.Portfolio;
import use_case.sell.SellInputBoundary;
import use_case.sell.SellInputData;
import java.util.ArrayList;

public class SellController {
    private final SellInputBoundary sellUseCaseInteractor;

    public SellController(SellInputBoundary sellUseCaseInteractor) {
        this.sellUseCaseInteractor = sellUseCaseInteractor;

    }

    public ArrayList<String> getHeldStocks(Portfolio portfolio) {
        return sellUseCaseInteractor.getHeldStocks(portfolio);
    }

    public void execute(String ticker, int quantity, Portfolio portfolio) {
        SellInputData sellInputData = new SellInputData(ticker, quantity, portfolio);
        sellUseCaseInteractor.execute(sellInputData);
    }
}
