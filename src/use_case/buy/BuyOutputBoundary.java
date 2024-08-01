package use_case.buy;

public interface BuyOutputBoundary {
    void prepareSuccessView(BuyOutputData buyOutputData);
    void prepareFailView(String error);
}
