package com.openclassrooms.mddapi.exceptions;

/**
 * Custom exception class representing the scenario where a user is not subscribed to any themes.
 * This exception is thrown when an operation requires the user to be subscribed to at least one theme,
 * but the user does not have any theme subscriptions.
 */
public class NoSubscribedThemesException extends RuntimeException {

    /**
     * Constructs a new NoSubscribedThemesException with the specified detail message.
     *
     * @param message The detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public NoSubscribedThemesException(String message) {
        super(message);
    }
}
