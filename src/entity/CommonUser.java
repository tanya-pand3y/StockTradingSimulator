package entity;

import java.time.LocalDateTime;

class CommonUser implements User {

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;

    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    CommonUser(String name, String password, LocalDateTime creationTime) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
    }

    /**
     * Returns the name of the user
     * @return the username
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the user password
     * @return the user password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns a datetime object of the time the user was created
     * @return a datetime object of the time the user was created
     */
    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

}
