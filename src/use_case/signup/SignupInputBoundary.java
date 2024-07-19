package use_case.signup;

import use_case.sell.SellInputData;

public interface SignupInputBoundary {
    void execute(SignupInputData signupInputData);
}
