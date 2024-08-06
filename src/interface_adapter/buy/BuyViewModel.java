package interface_adapter.buy;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BuyViewModel extends ViewModel {
    public final String TICKER_LABEL = "Stock Ticker";
    public final String QUANTITY_LABEL = "Quantity";
    public final String EXECUTE_LABEL = "Buy";
    private String errorMessage;
    public static final String ERROR_MESSAGE_PROPERTY = "errorMessage";
    private BuyState state = new BuyState();

    public BuyViewModel() {
        super("buy");
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void setState(BuyState state) {
        support.firePropertyChange("state", this.state, state);
        this.state = state;
    }

    public BuyState getState() {
        return state;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        String oldErrorMessage = this.errorMessage;
        this.errorMessage = errorMessage;
        support.firePropertyChange(ERROR_MESSAGE_PROPERTY, oldErrorMessage, this.errorMessage);
    }
}
