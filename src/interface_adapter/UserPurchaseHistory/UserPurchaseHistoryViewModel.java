package interface_adapter.UserPurchaseHistory;

import interface_adapter.ViewModel;
import interface_adapter.sell.SellState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserPurchaseHistoryViewModel extends ViewModel {
    private final UserPurchaseHistoryState userPurchaseHistoryState = new UserPurchaseHistoryState();

    public UserPurchaseHistoryViewModel() {
        super("UserPurchaseHistory");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public UserPurchaseHistoryState getState() {
        return userPurchaseHistoryState;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
