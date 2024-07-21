package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewStockHistory.ViewStockHistoryController;
import interface_adapter.ViewStockHistory.ViewStockHistoryPresenter;
import interface_adapter.ViewStockHistory.ViewStockHistoryViewModel;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupViewModel;
import use_case.stock_history.ViewStockHistoryInputBoundary;
import use_case.stock_history.ViewStockHistoryInteractor;
import view.SignupView;
import view.ViewStockHistoryView;
import use_case.stock_history.ViewStockHistoryOutputBoundary;


import javax.swing.*;
import java.io.IOException;

public class ViewStockHistoryFactory {

    /** Prevent instantiation. */
    private ViewStockHistoryFactory() {}

    public static ViewStockHistoryView create(ViewManagerModel viewManagerModel, DashboardViewModel dashboardViewModel, ViewStockHistoryViewModel viewModel) {
        try {
            ViewStockHistoryController controller = createViewStockHistoryUseCase(viewManagerModel, dashboardViewModel, viewModel);
            return new ViewStockHistoryView(controller, viewModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not load stock history data.");
        }
        return null;
    }

    private static ViewStockHistoryController createViewStockHistoryUseCase(ViewManagerModel viewManagerModel, DashboardViewModel dashboardViewModel, ViewStockHistoryViewModel viewModel) {
        // Output boundary that will handle presenting stock history data.
        ViewStockHistoryOutputBoundary historyPresenter = new ViewStockHistoryPresenter(viewManagerModel, dashboardViewModel, viewModel);

        // Interactor that performs the use case logic.
        ViewStockHistoryInputBoundary interactor = new ViewStockHistoryInteractor(historyPresenter);

        // Controller that connects the interactor to the view model.
        return new ViewStockHistoryController(interactor);
    }
}