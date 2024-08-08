package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardState;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final DashboardViewModel dashboardViewModel;
    private final SignupViewModel signupViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, DashboardViewModel dashboardViewModel, SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.dashboardViewModel = dashboardViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the main view.
        System.out.println("LoginPresenter.prepareSuccessView");
        DashboardState dashboardState = dashboardViewModel.getState();
        dashboardState.setUsername(response.getUsername());
        this.dashboardViewModel.setState(dashboardState);
        this.dashboardViewModel.firePropertyChanged();

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        loginState.setLoggedIn(true);
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(dashboardViewModel.getViewName()); // Assuming "dashboard" is the view you switch to after login
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSignUpView() {
        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
