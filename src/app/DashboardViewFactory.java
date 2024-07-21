package app;

import data_access.FileUserDataAccessObject;
import data_access.StockQuantityDataAccessObject;
import data_access.UserSignupDataAccessInterface;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardController;
import interface_adapter.dashboard.DashboardPresenter;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.sell.SellViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.dashboard.DashboardInputBoundary;
import use_case.dashboard.DashboardInteractor;
import use_case.dashboard.DashboardOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.DashboardView;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class DashboardViewFactory {
    DashboardViewFactory() {}

    public static DashboardView create(ViewManagerModel viewManagerModel, DashboardViewModel dashboardViewModel, SellViewModel sellViewModel) {

        DashboardController dashboardController = createDashboardUseCase(viewManagerModel, dashboardViewModel, sellViewModel);
        return new DashboardView(dashboardViewModel, dashboardController);
    }

    private static DashboardController createDashboardUseCase(ViewManagerModel viewManagerModel, DashboardViewModel dashboardViewModel, SellViewModel sellViewModel){
        StockQuantityDataAccessObject stockQuantityDataAccessObject = new StockQuantityDataAccessObject();

        // Notice how we pass this method's parameters to the Presenter.
        DashboardOutputBoundary dashboardPresenter = new DashboardPresenter(viewManagerModel, sellViewModel, dashboardViewModel);

        DashboardInputBoundary dashboardInteractor = new DashboardInteractor(stockQuantityDataAccessObject, dashboardPresenter);

        return new DashboardController(dashboardInteractor);
    }
}
