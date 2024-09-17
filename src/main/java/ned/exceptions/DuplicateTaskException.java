package ned.exceptions;

/**
 * The {@code DuplicateTaskException} is a custom exception that is thrown when
 * an attempt is made to add a task that already exists in the task list.
 * This exception extends the {@link NedException} class, allowing for specific handling
 * of duplicate tasks within the application.
 */
public class DuplicateTaskException extends NedException {

    /**
     * Constructs a new {@code DuplicateTaskException} with the specified error message.
     *
     * @param errorMessage The detail message explaining the reason for the exception.
     */
    public DuplicateTaskException(String errorMessage) {
        super(errorMessage);
    }
}
