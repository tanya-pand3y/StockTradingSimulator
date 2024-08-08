package use_case.login;

import data_access.UserLoginDataAccessInterface;
import entity.User;

public class LoginInteractor implements LoginInputBoundary {
    final UserLoginDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(UserLoginDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        System.out.println("Inside Interactor");

        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();

        if (!userDataAccessObject.existsByName(username)) {
            System.out.println("User not found" + username);
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                System.out.println("Wrong password" + username);
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {
                System.out.println(username + " in interactor");
                User user = userDataAccessObject.get(loginInputData.getUsername());

                LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    @Override
    public void prepareSignUpView() {
        this.loginPresenter.prepareSignUpView();
    }


}
