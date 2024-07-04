package src.entity;

public class User {
    private int id;
    private String username;
    private String password;
    private Portfolio portfolio;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        portfolio = new Portfolio(1000000);
    }
}
