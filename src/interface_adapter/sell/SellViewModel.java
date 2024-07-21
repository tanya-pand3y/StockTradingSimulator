package interface_adapter.sell;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SellViewModel extends ViewModel {
    public final String TITLE_LABEL = "Sell menu";
    public final String SELECT_LABEL = "Select stock:";
    public final String QUANTITY_LABEL = "Quantity:";
    public final String EXECUTE_LABEL = "Sell";
    private final SellState state = new SellState();

    public SellViewModel() {
        super("sell");
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
