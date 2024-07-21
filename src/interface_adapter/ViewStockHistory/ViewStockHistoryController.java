package interface_adapter.ViewStockHistory;

import use_case.stock_history.ViewStockHistoryInputBoundary;
import use_case.stock_history.ViewStockHistoryInputData;

public class ViewStockHistoryController {
    final ViewStockHistoryInputBoundary viewStockHistoryInteractor;

    public ViewStockHistoryController(ViewStockHistoryInputBoundary viewStockHistoryInteractor) {
    this.viewStockHistoryInteractor = viewStockHistoryInteractor;
    }

    public void execute(String ticker, String startDate, String endDate){
        ViewStockHistoryInputData inputData = new ViewStockHistoryInputData(ticker, startDate, endDate);
        viewStockHistoryInteractor.execute(inputData);
    }
}
