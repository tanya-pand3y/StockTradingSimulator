package entity;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class userTest implements User {
    private final String name;
    private final String password;
    private final LocalDateTime creationTime;

    public userTest(String name, String password, LocalDateTime creationTime) {
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
