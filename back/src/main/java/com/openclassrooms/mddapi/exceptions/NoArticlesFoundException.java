package com.openclassrooms.mddapi.exceptions;

/**
 * Custom exception class representing the scenario where no articles are found.
 * This exception is thrown when a query for articles yields no results.
 */
public class NoArticlesFoundException extends RuntimeException {

    /**
     * Constructs a new NoArticlesFoundException with the specified detail message.
     *
     * @param message The detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public NoArticlesFoundException(String message) {
        super(message);
    }
}
