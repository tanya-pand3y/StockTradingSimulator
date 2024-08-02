package use_case.sell;

import entity.Portfolio;
import entity.Stock;
import java.util.ArrayList;

public interface SellInputBoundary {
    /**
     * Executes a sell order
     * @param sellInputData the input data of the sell order to execute
     */
    void execute(SellInputData sellInputData);
    ArrayList<String> getHeldStocks(Portfolio portfolio);
}
