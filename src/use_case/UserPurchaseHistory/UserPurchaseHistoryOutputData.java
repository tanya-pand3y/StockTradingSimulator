package use_case.UserPurchaseHistory;

import entity.UserPurchaseHistory;
import java.util.List;

public class UserPurchaseHistoryOutputData {
    private final List<UserPurchaseHistory> purchaseHistory;

    public UserPurchaseHistoryOutputData(List<UserPurchaseHistory> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    public List<UserPurchaseHistory> getPurchaseHistory() {
        return purchaseHistory;
    }
}
