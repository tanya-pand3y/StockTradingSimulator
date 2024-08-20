package interface_adapter;

import java.beans.PropertyChangeListener;

public abstract class ViewModel {

    private String viewName;

    /**
     * Creates a view model with a name
     * @param viewName the name
     */
    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    /**
     * Returns the view name
     * @return the view name
     */
    public String getViewName() {
        return this.viewName;
    }

    public abstract void firePropertyChanged();
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);


}
