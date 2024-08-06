package app;

import data_access.StockCurrentAPIDataAccessObject;
import data_access.StockQuantityDataAccessObject;
import data_access.TiingoAPIDataAccessInterface;
import interface_adapter.ViewManagerModel;
import interface_adapter.buy.BuyController;
import interface_adapter.buy.BuyPresenter;
import interface_adapter.buy.BuyViewModel;
import interface_adapter.dashboard.DashboardViewModel;
import use_case.buy.BuyInputBoundary;
import use_case.buy.BuyInteractor;
import use_case.buy.BuyOutputBoundary;
import view.BuyView;

public class BuyViewFactory {
    BuyViewFactory() {};

    public static BuyView create(ViewManagerModel viewManagerModel,
                                 DashboardViewModel dashboardViewModel,
                                 BuyViewModel buyViewModel) {
        BuyController buyController = createBuyUseCase(viewManagerModel, dashboardViewModel, buyViewModel);
        return new BuyView(buyViewModel, buyController);
    }

    private static BuyController createBuyUseCase(ViewManagerModel viewManagerModel,
                                                  DashboardViewModel dashboardViewModel,
                                                  BuyViewModel buyViewModel) {
        StockQuantityDataAccessObject stockQuantityDataAccessObject = new StockQuantityDataAccessObject();
        BuyOutputBoundary buyPresenter = new BuyPresenter(buyViewModel, dashboardViewModel, viewManagerModel);
        BuyInputBoundary buyInteractor = new BuyInteractor(buyPresenter, stockQuantityDataAccessObject);
        return new BuyController(buyInteractor);
    }
}
