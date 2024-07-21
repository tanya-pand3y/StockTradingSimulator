package interface_adapter.ViewStockHistory;
import use_case.stock_history.ViewStockHistoryInputBoundary;
import use_case.stock_history.ViewStockHistoryInputData;

public class ViewStockHistoryController {
    private final ViewStockHistoryInputBoundary interactor;
    private final ViewStockHistoryPresenter presenter;

    // Constructor
    public ViewStockHistoryController(ViewStockHistoryInputBoundary interactor, ViewStockHistoryPresenter presenter) {
        this.interactor = interactor;
        this.presenter = presenter;
    }

    // Method to handle the stock history request
    public ViewStockHistoryViewModel getStockHistory(String ticker, String startDate, String endDate) {
        ViewStockHistoryInputData inputData = new ViewStockHistoryInputData(ticker, startDate, endDate);
        interactor.execute(inputData);
        return presenter.getViewModel();
    }
}