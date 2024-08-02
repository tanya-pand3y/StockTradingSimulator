package interface_adapter.dashboard;

import entity.Holding;
import entity.Portfolio;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class DashboardViewModel extends ViewModel{
    public final String TITLE_LABEL = "Dashboard";
    public final String WELCOME_LABEL = "Welcome, ";
    public final String PORTFOLIO_LABEL = "Portfolio Value: " ;
    public final String BUY_BUTTON_LABEL = "Buy";
    public final String SELL_BUTTON_LABEL = "Sell";


    public DashboardViewModel() {super("dashboard");}

    private DashboardState state = new DashboardState();

    public void setState(DashboardState state) {
        this.state = state;
    }

    public DashboardState getState() {
        return state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("UserLoggedIn", null, this.state);
    }

    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
