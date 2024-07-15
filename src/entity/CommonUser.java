package entity;

import java.time.LocalDateTime;

class CommonUser implements User {

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;
    private final Portfolio portfolio;

    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    CommonUser(String name, String password, LocalDateTime creationTime) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.portfolio = new Portfolio(1000);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}
