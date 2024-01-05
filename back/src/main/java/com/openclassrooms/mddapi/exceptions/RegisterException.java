package com.openclassrooms.mddapi.exceptions;

/**
 * Exception class for handling registration-related errors.
 * This exception is typically thrown when a user registration process encounters specific issues,
 * such as when a user attempts to register with an email that is already in use.
 */
public class RegisterException extends RuntimeException {

    /**
     * Constructs a new RegisterException with a specific error message.
     * The message provides more details about the error encountered during the user registration process.
     *
     * @param message The detail message that explains the reason for the exception. This message
     *                is saved for later retrieval by the Throwable.getMessage() method.
     */
    public RegisterException(String message) {
        super(message);
    }
}
