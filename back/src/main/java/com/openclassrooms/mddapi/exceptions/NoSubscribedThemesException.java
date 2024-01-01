package com.openclassrooms.mddapi.exceptions;

public class NoSubscribedThemesException extends RuntimeException {
    public NoSubscribedThemesException(String message) {
        super(message);
    }
}
