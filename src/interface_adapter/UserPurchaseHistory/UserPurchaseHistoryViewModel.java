package interface_adapter.UserPurchaseHistory;

import interface_adapter.ViewModel;
import interface_adapter.sell.SellState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserPurchaseHistoryViewModel extends ViewModel {
    private UserPurchaseHistoryState userPurchaseHistoryState = new UserPurchaseHistoryState();

    /**
     * Creates a user purchase history view model
     */
    public UserPurchaseHistoryViewModel() {
        super("UserPurchaseHistory");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Returns the user purchase history state
     * @return the user purchase history state
     */
    public UserPurchaseHistoryState getState() {
        return userPurchaseHistoryState;
    }

    /**
     * Sets the user purchase history state
     * @param state the user purchase history state
     */
    public void setState(UserPurchaseHistoryState state) {this.userPurchaseHistoryState = state;}

    /**
     * Updates for a property change
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.userPurchaseHistoryState);
    }

    /**
     * Adds a listener
     * @param listener the listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
