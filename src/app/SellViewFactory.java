package app;

import data_access.StockQuantityDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.sell.SellController;
import interface_adapter.sell.SellPresenter;
import interface_adapter.sell.SellViewModel;
import use_case.sell.SellInputBoundary;
import use_case.sell.SellInteractor;
import use_case.sell.SellOutputBoundary;
import view.SellView;

public class SellViewFactory {
    SellViewFactory() {}

    public static SellView create(ViewManagerModel viewManagerModel,
                                  DashboardViewModel dashboardViewModel,
                                  SellViewModel sellViewModel) {
        SellController sellController = createSellUseCase(viewManagerModel, dashboardViewModel, sellViewModel);
        return new SellView(sellViewModel, sellController);
    }

    private static SellController createSellUseCase(ViewManagerModel viewManagerModel,
                                                    DashboardViewModel dashboardViewModel,
                                                    SellViewModel sellViewModel) {
        StockQuantityDataAccessObject stockQuantityDataAccessObject = new StockQuantityDataAccessObject();

        SellOutputBoundary sellPresenter = new SellPresenter(dashboardViewModel, sellViewModel, viewManagerModel);
        SellInputBoundary sellInteractor = new SellInteractor(sellPresenter, stockQuantityDataAccessObject);

        return new SellController(sellInteractor);
    }
}
