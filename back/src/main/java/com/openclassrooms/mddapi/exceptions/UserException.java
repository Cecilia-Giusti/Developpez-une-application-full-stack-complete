package com.openclassrooms.mddapi.exceptions;

/**
 * Custom exception class to handle login-related issues.
 * This exception is thrown when there are issues specific to user login operations.
 */
public class UserException extends RuntimeException {

    /**
     * Constructs a new LoginException with the specified error message.
     * @param message The detail message explaining the reason for the exception.
     */
    public UserException(String message) {
        super(message);
    }
}
