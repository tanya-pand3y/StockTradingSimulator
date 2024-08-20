package interface_adapter.signup;

public class SignupState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;

    public SignupState(SignupState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
        repeatPassword = copy.repeatPassword;
        repeatPasswordError = copy.repeatPasswordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SignupState() {}

    /**
     * Returns the username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the username error
     * @return the username error
     */
    public String getUsernameError() {
        return usernameError;
    }

    /**
     * Returns the password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the username
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the username error
     * @param usernameError the username error
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * Set the password
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
