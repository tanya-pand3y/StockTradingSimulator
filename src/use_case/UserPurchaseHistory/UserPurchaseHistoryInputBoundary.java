package use_case.UserPurchaseHistory;

import entity.Portfolio;

public interface UserPurchaseHistoryInputBoundary {
    void retrievePurchaseHistory(UserPurchaseHistoryInputData inputData);

    void getUserHistoryArrays(Portfolio portfolio);
}

