package steve;
/**
 * Represents an exception that is caused by the user.
 */
public class UserException extends Exception {
    /**
     * Returns a new UserException object.
     * @param message The message of the exception.
     */
    public UserException(String message) {
        super(message);
    }

    public UserException() {
        super();
    }
}