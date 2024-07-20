package interface_adapter.dashboard;

public class DashboardState {
    private String username = "";

    public DashboardState(DashboardState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public DashboardState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
