package app;

import interface_adapter.QueryStock.QueryStockController;
import interface_adapter.QueryStock.QueryStockPresenter;
import interface_adapter.QueryStock.QueryStockViewModel;
import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryController;
import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryPresenter;
import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;
import use_case.UserPurchaseHistory.UserPurchaseHistoryInputBoundary;
import use_case.UserPurchaseHistory.UserPurchaseHistoryInteractor;
import use_case.UserPurchaseHistory.UserPurchaseHistoryOutputBoundary;
import use_case.query_stock.QueryStockInteractor;
import use_case.query_stock.QueryStockOutputBoundary;
import view.QueryStockView;

import java.io.IOException;

public class QueryStockFactory {
    public static QueryStockView create(ViewManagerModel viewManagerModel, QueryStockViewModel queryStockViewModel, DashboardViewModel dashboardViewModel) throws IOException {
        QueryStockController queryStockController = createQueryStockUseCase(viewManagerModel, queryStockViewModel, dashboardViewModel);
        return new QueryStockView(queryStockController, queryStockViewModel);
    }
    private static QueryStockController createQueryStockUseCase(ViewManagerModel viewManagerModel, QueryStockViewModel queryStockViewModel, DashboardViewModel dashboardViewModel) throws IOException {
        // Notice how we pass this method's parameters to the Presenter.
        QueryStockOutputBoundary queryStockPresent = new QueryStockPresenter(viewManagerModel, queryStockViewModel, dashboardViewModel);
        QueryStockInteractor queryStockInteractor = new QueryStockInteractor(queryStockPresent);
        return new QueryStockController(queryStockInteractor);
    }
}
