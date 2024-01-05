package com.openclassrooms.mddapi.exceptions;

/**
 * Exception class for handling user-related errors.
 * This exception is typically thrown when there are issues specific to user operations,
 * such as updating user details or performing actions that require specific user permissions.
 */
public class UserException extends RuntimeException {

    /**
     * Constructs a new UserException with a specific error message.
     * The message provides more details about the error encountered in user-related operations.
     *
     * @param message The detail message that explains the reason for the exception. This message
     *                is saved for later retrieval by the Throwable.getMessage() method.
     */
    public UserException(String message) {
        super(message);
    }
}
