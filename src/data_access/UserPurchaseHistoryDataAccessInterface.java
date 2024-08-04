package data_access;

import entity.UserPurchaseHistory;
import java.util.List;

public interface UserPurchaseHistoryDataAccessInterface {
    List<UserPurchaseHistory> getUserPurchaseHistory(String userId, String ticker);
}

