package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import data_access.FileUserDataAccessObject;
import data_access.UserLoginDataAccessInterface;
import entity.CommonUserFactory;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import view.LoginView;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

/**
 * Creates Login instances
 */
public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    /**
     * Creates a Login View
     * @param viewManagerModel The View Manager Model
     * @param loginViewModel The Login View Model
     * @param dashboardViewModel The Dashboard View Model
     * @param signupViewModel The Signup View Model
     * @return the Login View
     */
    public static LoginView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, DashboardViewModel dashboardViewModel, SignupViewModel signupViewModel) {

        try {
            LoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel, dashboardViewModel, signupViewModel);
            return new LoginView(loginViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, DashboardViewModel dashboardViewModel, SignupViewModel signupViewModel) throws IOException {
        UserLoginDataAccessInterface userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());

        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loginViewModel, dashboardViewModel, signupViewModel);

        LoginInputBoundary userLoginInteractor = new LoginInteractor(userDataAccessObject, loginOutputBoundary);

        return new LoginController(userLoginInteractor);
    }
}
