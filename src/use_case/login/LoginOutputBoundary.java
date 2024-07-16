package use_case.login;

import use_case.signup.SignupOutputData;

public interface LoginOutputBoundary {
    void prepareSuccessView(SignupOutputData user);

    void prepareFailView(String error);
}
