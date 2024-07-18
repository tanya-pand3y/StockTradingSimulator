package use_case.sell;

public interface SellInputBoundary {
    /**
     * Executes a sell order
     * @param sellInputData the input data of the sell order to execute
     */
    void execute(SellInputData sellInputData);
}
