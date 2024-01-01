package com.openclassrooms.mddapi.exceptions;

public class NoArticlesFoundException extends RuntimeException {
    public NoArticlesFoundException(String message) {
        super(message);
    }
}

