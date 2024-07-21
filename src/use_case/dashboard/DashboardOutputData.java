package use_case.dashboard;

public class DashboardOutputData {
    private final String username;

    public DashboardOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
