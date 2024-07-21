package use_case.stock_history;

public class ViewStockHistoryPresenter implements ViewStockHistoryOutputBoundary {
    @Override
    public void present(ViewStockHistoryOutputData outputData) {
        // Logic to present the output data, e.g., formatting or printing it
        System.out.println("Stock History for " + outputData.getTicker() + ": " + outputData.getStockPrices());
    }
}