package use_case.sell;

public interface SellOutputBoundary {
    /**
     * Prepares the view given output data
     * @param sellOutputData the output data
     */
    void prepareSuccessView(SellOutputData sellOutputData);

    /**
     * Prepares a fail view given an error
     * @param error the error
     */
    void prepareFailView(String error);
}
