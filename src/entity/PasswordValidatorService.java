package entity;

public class PasswordValidatorService implements PasswordValidator {
    /**
     * Returns if the password is valid
     * @param password the password
     * @return if the password is valid
     */
    public boolean passwordIsValid(String password) {
        return password != null && password.length() > 5;
    }
}
