package use_case.buy;

public interface BuyInputBoundary {
    void execute(BuyInputData buyInputData);
    void cancel();
    double getSharePrice(String ticker);
}
