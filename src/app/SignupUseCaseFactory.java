package app;

import data_access.StockQuantityDataAccessInterface;
import data_access.StockQuantityDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import data_access.FileUserDataAccessObject;
import data_access.UserSignupDataAccessInterface;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.*;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.SignupView;
import javax.swing.*;
import java.io.IOException;

/**
 * Creates signup views
 */
public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    /**
     * Creates a signup view
     * @param viewManagerModel the view manager model
     * @param signupViewModel the signup view model
     * @param dashboardViewModel the dashboard view model
     * @return the signup view
     */
    public static SignupView create(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, DashboardViewModel dashboardViewModel) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, dashboardViewModel);
            return new SignupView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, DashboardViewModel dashboardViewModel) throws IOException {
        UserSignupDataAccessInterface userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        StockQuantityDataAccessInterface stockQuantityDataAccessObject = new StockQuantityDataAccessObject();

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, dashboardViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory, stockQuantityDataAccessObject);

        return new SignupController(userSignupInteractor);
    }
}
