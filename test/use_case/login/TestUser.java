package use_case.login;

import entity.User;

import java.time.LocalDateTime;

public class TestUser implements User {

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;

    public TestUser(String name, String password, LocalDateTime creationTime) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
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
}
