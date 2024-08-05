package app;

import data_access.FileUserDataAccessObject;
import data_access.StockQuantityDataAccessInterface;
import data_access.StockQuantityDataAccessObject;
import data_access.UserSignupDataAccessInterface;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryController;
import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryPresenter;
import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.UserPurchaseHistory.UserPurchaseHistoryInputBoundary;
import use_case.UserPurchaseHistory.UserPurchaseHistoryInteractor;
import use_case.UserPurchaseHistory.UserPurchaseHistoryOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.DashboardView;
import view.SignupView;
import view.UserPurchaseHistoryView;

import javax.swing.*;
import java.io.IOException;

public class UserPurchaseHistoryFactory {
    public static UserPurchaseHistoryView create(ViewManagerModel viewManagerModel, UserPurchaseHistoryViewModel userPurchaseHistoryViewModel, DashboardViewModel dashboardViewModel) {

        try {
            UserPurchaseHistoryController controller = createUserPurchaseHistoryUseCase(viewManagerModel, userPurchaseHistoryViewModel, dashboardViewModel);
            return new UserPurchaseHistoryView(userPurchaseHistoryViewModel, controller);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static UserPurchaseHistoryController createUserPurchaseHistoryUseCase(ViewManagerModel viewManagerModel, UserPurchaseHistoryViewModel userPurchaseHistoryViewModel, DashboardViewModel dashboardViewModel) throws IOException {
        // Notice how we pass this method's parameters to the Presenter.
        UserPurchaseHistoryOutputBoundary userPurchaseHistoryPresenter = new UserPurchaseHistoryPresenter(viewManagerModel, userPurchaseHistoryViewModel, dashboardViewModel);
        UserPurchaseHistoryInputBoundary userPurchaseHistoryInteractor = new UserPurchaseHistoryInteractor(userPurchaseHistoryPresenter);

        return new UserPurchaseHistoryController(userPurchaseHistoryInteractor);
    }
}
