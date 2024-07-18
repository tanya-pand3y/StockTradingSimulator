package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class LoginController {

    private final LoginInputBoundary userLoginUseCaseInteractor;

    public LoginController(LoginInputBoundary userLoginUseCaseInteractor) {
        this.userLoginUseCaseInteractor = userLoginUseCaseInteractor;
    }

    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);
        userLoginUseCaseInteractor.execute(loginInputData);
    }
}
// edited
