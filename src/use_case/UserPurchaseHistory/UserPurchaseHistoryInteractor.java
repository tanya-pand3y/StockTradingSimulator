package use_case.UserPurchaseHistory;

import data_access.UserPurchaseHistoryDataAccessInterface;
import entity.Portfolio;
import entity.UserPurchaseHistory;
import java.util.List;

public class UserPurchaseHistoryInteractor implements UserPurchaseHistoryInputBoundary {

    private final UserPurchaseHistoryDataAccessInterface dataAccess;
    private final UserPurchaseHistoryOutputBoundary outputBoundary;

    public UserPurchaseHistoryInteractor(UserPurchaseHistoryDataAccessInterface dataAccess,
                                         UserPurchaseHistoryOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void retrievePurchaseHistory(UserPurchaseHistoryInputData inputData) {
        List<UserPurchaseHistory> history = dataAccess.getUserPurchaseHistory(inputData.getUserId(), inputData.getTicker());
        history.sort((a, b) -> a.getDate().compareTo(b.getDate())); // Sort in ascending order

        UserPurchaseHistoryOutputData outputData = new UserPurchaseHistoryOutputData(history);
        outputBoundary.presentPurchaseHistory(outputData);
    }

    @Override
    public void getUserHistoryArrays(Portfolio portfolio) {

    }


}

