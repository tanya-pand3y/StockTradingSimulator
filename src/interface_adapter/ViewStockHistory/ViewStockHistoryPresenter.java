package interface_adapter.ViewStockHistory;

import use_case.stock_history.ViewStockHistoryOutputBoundary;
import use_case.stock_history.ViewStockHistoryOutputData;

public class ViewStockHistoryPresenter implements ViewStockHistoryOutputBoundary {
    private ViewStockHistoryViewModel viewModel;

    @Override
    public void present(ViewStockHistoryOutputData outputData) {
        viewModel = new ViewStockHistoryViewModel(outputData.getTicker(), outputData.getStockPrices());
    }

    public ViewStockHistoryViewModel getViewModel() {
        return viewModel;
    }
}