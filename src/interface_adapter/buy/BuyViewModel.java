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
    private double sharePrice;
    public static final String SHARE_PRICE_PROPERTY = "sharePrice";
    private BuyState state = new BuyState();

    public BuyViewModel() {
        super("buy");
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Updates given a property change
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener
     * @param pcl the property change listener
     */
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    /**
     * Removes a property change listener
     * @param pcl the property change listener
     */
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    /**
     * Sets the buy state
     * @param state the buy state
     */
    public void setState(BuyState state) {
        support.firePropertyChange("state", this.state, state);
        this.state = state;
    }

    /**
     * Sets the share price
     * @param sharePrice the share price
     */
    public void setSharePrice(double sharePrice) {
        double oldSharePrice = this.sharePrice;
        this.sharePrice = sharePrice;
        support.firePropertyChange(SHARE_PRICE_PROPERTY, oldSharePrice, sharePrice);
    }

    /**
     * returns the buy state
     * @return the buy state
     */
    public BuyState getState() {
        return state;
    }

    /**
     * Gets the error message
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        String oldErrorMessage = this.errorMessage;
        this.errorMessage = errorMessage;
        support.firePropertyChange(ERROR_MESSAGE_PROPERTY, oldErrorMessage, this.errorMessage);
    }
}
