package interface_adapter.sell;

import entity.Stock;
import entity.User;
import use_case.sell.SellInputBoundary;
import use_case.sell.SellInputData;
import java.util.ArrayList;

public class SellController {
    private final SellInputBoundary sellUseCaseInteractor;

    public SellController(SellInputBoundary sellUseCaseInteractor) {
        this.sellUseCaseInteractor = sellUseCaseInteractor;

    }

    public String[] getHeldStocks() {
        return sellUseCaseInteractor.getHeldStocks();
    }

    public void execute(Stock stock, int quantity, double price, User user) {
        SellInputData sellInputData = new SellInputData(stock, quantity, price, user);
        sellUseCaseInteractor.execute(sellInputData);
    }
}
