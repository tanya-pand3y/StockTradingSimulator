package interface_adapter.ViewStockHistory;

import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;
import use_case.stock_history.ViewStockHistoryOutputBoundary;
import use_case.stock_history.ViewStockHistoryOutputData;

public class ViewStockHistoryPresenter implements ViewStockHistoryOutputBoundary {
    private final ViewStockHistoryViewModel stockHistoryViewModel;
    private final ViewManagerModel viewManagerModel;
    private final DashboardViewModel dashboardViewModel;


    public ViewStockHistoryPresenter(ViewManagerModel viewManagerModel, DashboardViewModel dashboardViewModel, ViewStockHistoryViewModel stockHistoryViewModel){
        this.stockHistoryViewModel = stockHistoryViewModel;
        this.viewManagerModel = viewManagerModel;
        this.dashboardViewModel = dashboardViewModel;
    }

    @Override
    public void present(ViewStockHistoryOutputData outputData) {
        // Logic to present the output data, e.g., formatting or printing it
        //Prepare success view
        System.out.println("Stock History for " + outputData.getTicker() + ": " + outputData.getStockPrices());
        viewManagerModel.setActiveView(dashboardViewModel.getViewName());

    }
}