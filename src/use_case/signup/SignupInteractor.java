package use_case.signup;

import data_access.StockQuantityDataAccessInterface;
import data_access.StockQuantityDataAccessObject;
import data_access.UserSignupDataAccessInterface;
import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    final UserSignupDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;
    final StockQuantityDataAccessInterface stockQuantityDataAccessObject;

    public SignupInteractor(UserSignupDataAccessInterface userSignupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory, StockQuantityDataAccessInterface stockQuantityDataAccessObject) {

        this.userDataAccessObject = userSignupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
        this.stockQuantityDataAccessObject = stockQuantityDataAccessObject;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

            LocalDateTime now = LocalDateTime.now();
            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), now);
            userDataAccessObject.save(user);

            this.stockQuantityDataAccessObject.createUserCSV(signupInputData.getUsername());

            SignupOutputData signupOutputData = new SignupOutputData(user.getName(), now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
