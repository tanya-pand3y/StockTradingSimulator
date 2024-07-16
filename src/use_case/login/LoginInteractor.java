package use_case.login;

import java.time.LocalDateTime;

public class LoginInteractor implements LoginInputBoundary{
    final UserLoginDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary userPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userLoginDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }
}
