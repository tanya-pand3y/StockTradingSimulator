package entity;

public interface PasswordValidator {
    /**
     * Returns if a given password is valid
     * @param password the password
     * @return if it is valid
     */
    public boolean passwordIsValid(String password);

}
