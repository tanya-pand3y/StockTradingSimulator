package interface_adapter.ViewStockHistory;


import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;
import use_case.stock_history.ViewStockHistoryInputData;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewStockHistoryViewModel extends ViewModel {
    public final String TITLE_LABEL = "View stock history";
    public final String SEARCH_LABEL = "Enter stock ticker:";
    public final String SELECT_START_DATE_LABEL = "Select start date:";
    public final String SELECT_END_DATE_LABEL = "Select end date:";
    public final String EXECUTE_LABEL = "View history";

    private ViewStockHistoryState state = new ViewStockHistoryState();


    public ViewStockHistoryViewModel() {
        super("View stock history");
    }

    public void setState(ViewStockHistoryState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ViewStockHistoryState getState() {
        return state;
    }

}
