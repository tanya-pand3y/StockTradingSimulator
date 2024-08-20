package interface_adapter.sell;

import entity.Portfolio;
import use_case.sell.SellInputBoundary;
import use_case.sell.SellInputData;
import java.util.ArrayList;

public class SellController {
    private final SellInputBoundary sellUseCaseInteractor;

    /**
     * Creates a sell controller
     * @param sellUseCaseInteractor the use case interactor
     */
    public SellController(SellInputBoundary sellUseCaseInteractor) {
        this.sellUseCaseInteractor = sellUseCaseInteractor;

    }

    /**
     * Returns the held stocks
     * @param portfolio the portfolio
     * @return the held stocks
     */
    public ArrayList<String> getHeldStocks(Portfolio portfolio) {
        return sellUseCaseInteractor.getHeldStocks(portfolio);
    }

    /**
     * Executes sell
     * @param ticker the ticker
     * @param quantity the quantity
     * @param portfolio the portfolio
     */
    public void execute(String ticker, int quantity, Portfolio portfolio) {
        SellInputData sellInputData = new SellInputData(ticker, quantity, portfolio);
        sellUseCaseInteractor.execute(sellInputData);
    }
}
